
package frc.robot;

import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.drive.DriveModule;
import org.montclairrobotics.sprocket.drive.DriveTrain;
import org.montclairrobotics.sprocket.drive.DriveTrainBuilder;
import org.montclairrobotics.sprocket.drive.DriveTrainType;
import org.montclairrobotics.sprocket.drive.InvalidDriveTrainException;
import org.montclairrobotics.sprocket.geometry.XY;
import org.montclairrobotics.sprocket.motors.Motor;

public class Robot extends SprocketRobot {
    DriveTrain dt;

    @Override
    public void robotInit(){
        Hardware.init();
        // Drivetrain code
        // Todo: test and implement Gyro Lock
        DriveTrainBuilder dtBuilder = new DriveTrainBuilder();
        dtBuilder.addDriveModule(new DriveModule(new XY(1, 0), new XY(0, 1), new Motor(Hardware.dt_rightFront), new Motor(Hardware.dt_rightBack)));
        dtBuilder.addDriveModule(new DriveModule(new XY(1, 0), new XY(0, 1), new Motor(Hardware.dt_rightFront), new Motor(Hardware.dt_rightBack)));
        
        dtBuilder.setInput(Control.dt_input);
        dtBuilder.setDriveTrainType(DriveTrainType.TANK);

        try {
            dt = dtBuilder.build();
        } catch (InvalidDriveTrainException e) {
            e.printStackTrace();
        }

        dt.setDefaultInput(Control.dt_input);
        
    }
}
