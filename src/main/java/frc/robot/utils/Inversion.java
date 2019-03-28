package frc.robot.utils;

import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.geometry.Degrees;

public class Inversion implements DTStep {
    @Override
    public DTTarget get(DTTarget dtTarget) {
        return new DTTarget(dtTarget.getDirection().rotate(new Degrees(180)), dtTarget.getTurn().times(-1));
    }
}
