package frc.robot.utils;

import edu.wpi.first.wpilibj.SpeedController;
import org.montclairrobotics.sprocket.motors.Motor;
import org.montclairrobotics.sprocket.utils.Input;

public class LimitedMotor extends Motor {

    private Input<Boolean> bottomLimit;
    private Input<Boolean> topLimit;

    public LimitedMotor(SpeedController motor, Input<Boolean> bottomLimit, Input<Boolean> topLimit) {
        super(motor);
        this.bottomLimit = bottomLimit;
        this.topLimit = topLimit;
    }

    @Override
    public void set(double power) {
        if(power < 0 && bottomLimit.get()){
            super.set(0);
        }else if(power > 0 && topLimit.get()){
            super.set(0);
        }else{
            super.set(power);
        }
    }
}
