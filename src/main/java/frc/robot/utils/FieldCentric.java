package frc.robot;

import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.geometry.Degrees;
import org.montclairrobotics.sprocket.utils.Togglable;

public class FieldCentric implements DTStep, Togglable {

    GyroCorrection correction;
    boolean enabled = false;

    public FieldCentric(GyroCorrection correction){
        this.correction = correction;
    }

    @Override
    public DTTarget get(DTTarget dtTarget) {
        if(enabled) {
            return new DTTarget(dtTarget.getDirection().rotate(correction.getCurrentAngleReset().times(-1)), dtTarget.getTurn());
        }else{
            return dtTarget;
        }
    }

    @Override
    public void enable() {
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }
}
