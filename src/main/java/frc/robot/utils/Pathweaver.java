package frc.robot.utils;

import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.DriveTrain;
import org.montclairrobotics.sprocket.motors.*;
import org.montclairrobotics.sprocket.motors.Module;
import org.montclairrobotics.sprocket.utils.Togglable;

import edu.wpi.first.wpilibj.Notifier;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class Pathweaver implements Togglable{

    SprocketRobot sprocketRobot;
    DriveTrain driveTrain;

    Trajectory left_trajectory;
    Trajectory right_trajectory;

    SEncoder leftEncoder;
    SEncoder rightEncoder;

    Module leftModule, rightModule;

    NavXInput navXInput;

    EncoderFollower m_left_follower;
    EncoderFollower m_right_follower;
    Notifier m_follower_notifier;

    final int k_ticks_per_rev = 1024;
    final double k_wheel_diameter = 4.0 / 12.0;
    final double k_max_velocity = 10;

    public Pathweaver(SprocketRobot sprocketRobot, DriveTrain driveTrain, 
                    NavXInput navXInput, 
                    int leftModule, int rightModule){

        this.sprocketRobot = sprocketRobot;
        this.driveTrain = driveTrain;
        this.navXInput = navXInput;

        this.leftModule = driveTrain.getModules()[leftModule];
        this.rightModule = driveTrain.getModules()[rightModule];
    
        this.leftEncoder = this.leftModule.getEnc();
        this.rightEncoder = this.rightModule.getEnc();
    
    }

    public boolean init(){

        left_trajectory = PathfinderFRC.getTrajectory("test" + ".left");
        right_trajectory = PathfinderFRC.getTrajectory("test" + ".right");

        m_left_follower = new EncoderFollower(left_trajectory);
        m_right_follower = new EncoderFollower(right_trajectory);
    
        // TODO: Double check module
        m_left_follower.configureEncoder(leftEncoder.getTicks(),
                                    k_ticks_per_rev, 
                                    k_wheel_diameter);
        // You must tune the PID values on the following line!
        m_left_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);

        // TODO: Double check module
        m_right_follower.configureEncoder(rightEncoder.getTicks(),
                                    k_ticks_per_rev,
                                    k_wheel_diameter);
        // You must tune the PID values on the following line!
        m_right_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);
    
        m_follower_notifier = new Notifier(this::enable);
        m_follower_notifier.startPeriodic(left_trajectory.get(0).dt);

        return true;
    }

    @Override
    public void enable() {
        if (m_left_follower.isFinished() || m_right_follower.isFinished()) {
            m_follower_notifier.stop();
        } else {
            double left_speed = m_left_follower.calculate(leftEncoder.getTicks());
            double right_speed = m_right_follower.calculate(rightEncoder.getTicks());
            double heading = navXInput.getAngle();
            double desired_heading = Pathfinder.r2d(m_left_follower.getHeading());
            double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
            double turn =  0.8 * (-1.0/80.0) * heading_difference;
            leftModule.set(left_speed + turn);
            rightModule.set(right_speed - turn);
        }
    }

    @Override
    public void disable() {
        m_follower_notifier.stop();
        leftModule.set(0);
        rightModule.set(0);
    }
}