package frc.robot.test;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.utils.SP;
import frc.robot.core.Control;
import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.drive.ControlledMotor;
import org.montclairrobotics.sprocket.utils.Input;

public class MotorTest extends SprocketRobot {
    @Override
    public void robotInit() {
        Control.init();
        ControlledMotor m1 = new ControlledMotor(new SP(new VictorSP(3)), (Input<Double>) () -> Control.driveStick.getY());

    }

}
