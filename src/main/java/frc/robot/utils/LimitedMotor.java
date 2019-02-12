package frc.robot.utils;

import edu.wpi.first.wpilibj.SpeedController;
import org.montclairrobotics.sprocket.motors.Motor;
import org.montclairrobotics.sprocket.utils.Input;

public class LimitedMotor extends Motor {

    private Input<Boolean> limit;

    public LimitedMotor(SpeedController motor, Input<Boolean> limit) {
        super(motor);
        this.limit = limit;
    }

    @Override
    public void set(double power) {
        if(!limit.get()) {
            super.set(power);
        }else{
            super.set(0);
        }
    }
}
