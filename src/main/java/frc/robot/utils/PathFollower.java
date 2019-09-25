package frc.robot.utils;

import edu.wpi.first.wpilibj.Notifier;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.geometry.Angle;
import org.montclairrobotics.sprocket.geometry.Degrees;
import org.montclairrobotics.sprocket.geometry.Vector;
import org.montclairrobotics.sprocket.geometry.XY;
import org.montclairrobotics.sprocket.utils.PID;
import org.montclairrobotics.sprocket.utils.Togglable;

public class PathFollower implements DTStep, Togglable {

    private int segment;
    private Trajectory trajectory;
    private GyroCorrection correction;
    private double dt;
    private long prevUpdateTime;
    private boolean enabled;

    public PathFollower(Trajectory trajectory, GyroCorrection correction){
        this.trajectory = trajectory;
        this.dt = trajectory.get(0).dt;
        this.correction = correction;
    }

    @Override
    public DTTarget get(DTTarget dtTarget) {
        double elapsedTime = (System.currentTimeMillis() - prevUpdateTime) / 1000.0;
        if(segment < trajectory.segments.length && elapsedTime > dt && enabled){
            prevUpdateTime = System.currentTimeMillis();
            Trajectory.Segment seg = trajectory.get(segment);
            correction.setTargetAngle(new Degrees(seg.heading), false);

            segment++;

            return new DTTarget(new XY(0, seg.velocity), correction.get(dtTarget).getTurn());
        }else{
            return new DTTarget(Vector.ZERO, Angle.ZERO);
        }


    }

    public boolean isDone(){
        return segment >= trajectory.segments.length;
    }

    @Override
    public void enable() {
        segment = 0;
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }
}
