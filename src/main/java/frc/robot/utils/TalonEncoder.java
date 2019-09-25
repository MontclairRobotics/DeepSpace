package frc.robot.utils;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import org.montclairrobotics.sprocket.motors.SEncoder;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.montclairrobotics.sprocket.motors.SEncoder;
import org.montclairrobotics.sprocket.utils.Debug;

public class TalonEncoder extends SEncoder {
    private WPI_TalonSRX motor;
    private int zero = 0;
    public TalonEncoder(WPI_TalonSRX motor,double ticksPerInch) {
        super(null, ticksPerInch);
        this.motor = motor;
    }

    @Override
    public int getTicks() {
        return this.motor.getSensorCollection().getQuadraturePosition() - zero;
    }

    @Override
    public double getTickRate() {
        return this.motor.getSensorCollection().getQuadratureVelocity();
    }

    @Override
    public void reset()
    {
        zero = getTicks() + zero;
    }

    @Override
    public Double get() {
        Debug.msg(motor.getDescription(), getTicks());
        return super.get();
    }
}