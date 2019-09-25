package frc.robot.utils;

import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import org.montclairrobotics.sprocket.auto.AutoState;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.ZeroDTInput;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.geometry.Angle;
import org.montclairrobotics.sprocket.geometry.Degrees;
import org.montclairrobotics.sprocket.geometry.Vector;
import org.montclairrobotics.sprocket.geometry.XY;

public class PathState extends AutoState {

    private PathFollower follower;
    private static GyroCorrection correction;

    public PathState(String path){
        follower = new PathFollower(PathfinderFRC.getTrajectory(path),correction);
    }

    public PathState(PathFollower follower){
        this.follower = follower;
    }

    public static void setCorrection(GyroCorrection correction){
        PathState.correction = correction;
    }

    @Override
    public void stateUpdate() {
        DTTarget target = follower.get(new DTTarget(Vector.ZERO, Angle.ZERO));
        setTarget(target.getDirection(), target.getTurn());
    }

    @Override
    public boolean isDone() {
        return follower.isDone();
    }
}
