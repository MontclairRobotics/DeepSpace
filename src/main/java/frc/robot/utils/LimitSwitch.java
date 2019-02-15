package frc.robot.utils;

import edu.wpi.first.wpilibj.DigitalInput;
import org.montclairrobotics.sprocket.utils.Input;

public class LimitSwitch implements Input<Boolean> {
    public DigitalInput digitalInput;
    public LimitSwitch(int channel){
        digitalInput = new DigitalInput(channel);
    }

    @Override
    public Boolean get() {
        return digitalInput.get();
    }
}
