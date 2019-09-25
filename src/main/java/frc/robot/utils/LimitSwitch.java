package frc.robot.utils;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.montclairrobotics.sprocket.utils.Input;

public class LimitSwitch implements Input<Boolean> {
    private DigitalInput digitalInput;
    private int channel;
    private boolean alwaysOn;
    public LimitSwitch(int channel, boolean alwaysOn){
        digitalInput = new DigitalInput(channel);
        this.alwaysOn = alwaysOn;
        this.channel = channel;
    }

    @Override
    public Boolean get() {
        SmartDashboard.putBoolean("limit switch:" + channel, digitalInput.get() != alwaysOn);
        return digitalInput.get() != alwaysOn;
    }
}
