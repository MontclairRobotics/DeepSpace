package frc.robot.test;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
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
    SEncoder encoder1;
    SEncoder encoder2;
    SEncoder encoder3;
    SEncoder encoder4;

    DigitalInput limitSwitch;

    @Override
    public void robotInit() {
        Control.init();
        //Hardware.init();
        WPI_TalonSRX talonSRX1 = new WPI_TalonSRX(1);
        WPI_TalonSRX talonSRX2 = new WPI_TalonSRX(13);
        WPI_TalonSRX talonSRX3 = new WPI_TalonSRX(0);
        WPI_TalonSRX talonSRX4 = new WPI_TalonSRX(14);

        ControlledMotor m1 = new ControlledMotor(talonSRX1, () -> Control.auxStick.getRawAxis(1));
        ControlledMotor m2 = new ControlledMotor(talonSRX2, () -> Control.auxStick.getRawAxis(1));
        ControlledMotor m3 = new ControlledMotor(talonSRX3, () -> Control.auxStick.getRawAxis(1));
        ControlledMotor m4 = new ControlledMotor(talonSRX4, () -> Control.auxStick.getRawAxis(1));

        encoder1 = new SEncoder(new Encoder(0, 1), 1);
        encoder2 = new SEncoder(new Encoder(2, 3), 1);
        encoder3 = new SEncoder(new Encoder(4, 5), 1);
        encoder4 = new SEncoder(new Encoder(6, 7), 1);

        limitSwitch = new DigitalInput(23);

    }

    @Override
    public void userTeleopInit() {
        encoder1.reset();
        encoder2.reset();
        encoder3.reset();
        encoder4.reset();
    }

    @Override
    public void update() {
        Debug.msg("Encoder speed 1", encoder1.getSpeed());
        Debug.msg("Encoder speed 2", encoder2.getSpeed());
        Debug.msg("Encoder speed 3", encoder3.getSpeed());
        Debug.msg("Encoder speed 4", encoder4.getSpeed());
        Debug.msg("Limit Switch", limitSwitch.get());
    }
}
