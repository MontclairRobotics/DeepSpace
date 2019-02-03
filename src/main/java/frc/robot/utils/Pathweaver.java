package frc.robot.utils;

import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.DriveTrain;
import org.montclairrobotics.sprocket.geometry.Radians;
import org.montclairrobotics.sprocket.geometry.Vector;
import org.montclairrobotics.sprocket.geometry.XY;
import org.montclairrobotics.sprocket.motors.*;
import org.montclairrobotics.sprocket.motors.Module;
import org.montclairrobotics.sprocket.utils.PID;
import org.montclairrobotics.sprocket.utils.Togglable;

import edu.wpi.first.wpilibj.Notifier;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class Pathweaver implements DTStep, Togglable{

    Trajectory trajectory;
    boolean enabled;

    @Override
    public void enable() {
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }

    @Override
    public DTTarget get(DTTarget dtTarget) {
        trajectory = PathfinderFRC.getTrajectory("Test");
        if(enabled){
            for(int i=0; i<trajectory.segments.length; i++){
                return new DTTarget(
                        dtTarget.getDirection().add(new XY(trajectory.get(i).x, trajectory.get(i).y)),
                        dtTarget.getTurn().add(new Radians(trajectory.get(i).heading))
                );
            }
        }
        return dtTarget;
    }
}