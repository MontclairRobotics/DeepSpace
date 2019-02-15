package frc.robot.test;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.core.Hardware;
import frc.robot.utils.SP;
import frc.robot.core.Control;
import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.drive.ControlledMotor;
import org.montclairrobotics.sprocket.utils.Input;

public class MotorTest extends SprocketRobot {
    @Override
    public void robotInit() {
        Control.init();
        Hardware.init();
        ControlledMotor m1 = new ControlledMotor(new WPI_TalonSRX(12), (Input<Double>) () -> Control.auxStick.getRawAxis(1));
        ControlledMotor m2 = new ControlledMotor(new WPI_TalonSRX(2), (Input<Double>) () -> -Control.auxStick.getRawAxis(1));
    }

}
