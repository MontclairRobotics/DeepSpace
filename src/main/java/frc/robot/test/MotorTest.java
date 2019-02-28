package frc.robot.test;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.core.Hardware;
import frc.robot.utils.SP;
import frc.robot.core.Control;
import frc.robot.utils.TalonEncoder;
import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.drive.ControlledMotor;
import org.montclairrobotics.sprocket.motors.SEncoder;
import org.montclairrobotics.sprocket.utils.Debug;
import org.montclairrobotics.sprocket.utils.Input;

public class MotorTest extends SprocketRobot {
    SEncoder encoder;
    @Override
    public void robotInit() {
        Control.init();
        Hardware.init();
        WPI_TalonSRX talonSRX = new WPI_TalonSRX(3);
        ControlledMotor m1 = new ControlledMotor(talonSRX, () -> Control.auxStick.getRawAxis(1));
//        ControlledMotor m2 = new ControlledMotor(new WPI_TalonSRX(2), (Input<Double>) () -> Control.auxStick.getRawAxis(1));
        encoder = new TalonEncoder(talonSRX, 1);
    }

    @Override
    public void userTeleopInit() {
        encoder.reset();
    }

    @Override
    public void update() {
        Debug.msg("Test Encoder Values", encoder.getTicks());
    }
}
