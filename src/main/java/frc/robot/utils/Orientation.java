package frc.robot.utils;

import frc.robot.core.Control;
import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.drive.utils.GyroLock;
import org.montclairrobotics.sprocket.geometry.Degrees;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.utils.PID;
import org.montclairrobotics.sprocket.utils.Togglable;
import org.montclairrobotics.sprocket.utils.Utils;

public class Orientation implements DTStep, Updatable{

    GyroCorrection correction;
    private double previousAngle;

    public Orientation(GyroCorrection correction){
        this.correction = correction;
    }

    @Override
    public DTTarget get(DTTarget dtTarget) {
        if(Control.driveStick.getPOV() > 0){
            return correction.get(dtTarget);
        }else{
            return dtTarget;
        }
    }

    @Override
    public void update() {
        if(previousAngle != Control.driveStick.getPOV() && Control.driveStick.getPOV() > 1){
            correction.setTargetAngle(new Degrees(Utils.wrap(Control.driveStick.getPOV(), -180, 180)), false);
        }
        previousAngle = Control.driveStick.getPOV();
    }
}
