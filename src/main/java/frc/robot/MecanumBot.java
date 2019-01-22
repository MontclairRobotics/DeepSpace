package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.drive.*;
import org.montclairrobotics.sprocket.geometry.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.WPILibVersion;
import org.montclairrobotics.sprocket.motors.Motor;

public class MecanumBot extends SprocketRobot {

    DriveTrain dt;
    DriveTrainBuilder dtBuilder;

    @Override
    public void robotInit() {
        Hardware.init();
        dtBuilder = new DriveTrainBuilder();
        dtBuilder.addDriveModule(new DriveModule(new XY(-1,1), new Polar(1, new Degrees(135)), new Motor(Hardware.fl)));
        dtBuilder.addDriveModule(new DriveModule(new XY(1,1), new Polar(1, new Degrees(45)), new Motor(Hardware.fr)));
        dtBuilder.addDriveModule(new DriveModule(new XY(-1,-1), new Polar(1, new Degrees(135)), new Motor(Hardware.bl)));
        dtBuilder.addDriveModule(new DriveModule(new XY(1,-1), new Polar(1, new Degrees(45)), new Motor(Hardware.fl)));

        dtBuilder.setInput(Control.dtInput);
        dtBuilder.setDriveTrainType(DriveTrainType.MECANUM);

        try {
            dtBuilder.build();
        }
        catch (InvalidDriveTrainException e) {
            e.printStackTrace();
        }
    }
}
