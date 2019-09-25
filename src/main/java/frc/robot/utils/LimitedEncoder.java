package frc.robot.utils;

import edu.wpi.first.wpilibj.Encoder;

import org.montclairrobotics.sprocket.motors.SEncoder;
import org.montclairrobotics.sprocket.utils.Input;

public class LimitedEncoder {
    private final SEncoder encoder;
    private final int high;
    private final int low;

    private int offset;

    public LimitedEncoder(SEncoder encoder, int high, int low){
        this.encoder = encoder;
        this.high = high;
        this.low = low;
    }

    public void resetHigh(){
        // target 10, actual 9, offset 1 (target - actual)
        offset = high - encoder.getTicks();
    }

    public void resetLow(){
        // target 0, actual -1, offset 1 (target - actual)
        offset = low - encoder.getTicks();
    }

    public int get(){
        return encoder.getTicks() + offset;
    }

    public boolean getHigh(){
        return get() > high;
    }

    public boolean getLow(){
        return get()  < low;
    }
}
