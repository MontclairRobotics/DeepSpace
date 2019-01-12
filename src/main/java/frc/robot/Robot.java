
package frc.robot;

import java.util.ArrayList;

import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.drive.DTPipeline;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.DriveModule;
import org.montclairrobotics.sprocket.drive.DriveTrain;
import org.montclairrobotics.sprocket.drive.DriveTrainBuilder;
import org.montclairrobotics.sprocket.drive.DriveTrainType;
import org.montclairrobotics.sprocket.drive.InvalidDriveTrainException;
import org.montclairrobotics.sprocket.drive.steps.Deadzone;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.drive.utils.GyroLock;
import org.montclairrobotics.sprocket.geometry.XY;
import org.montclairrobotics.sprocket.motors.Motor;
import org.montclairrobotics.sprocket.pipeline.Step;
import org.montclairrobotics.sprocket.utils.Debug;
import org.montclairrobotics.sprocket.utils.PID;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Robot extends SprocketRobot {
    DriveTrain dt;
    GyroCorrection correction;
    GyroLock lock;

    @Override
    public void robotInit(){
        Hardware.init();
        Control.init();
        // Drivetrain code
        // Todo: test and implement Gyro Lock
        DriveTrainBuilder dtBuilder = new DriveTrainBuilder();
        dtBuilder.addDriveModule(new DriveModule(new XY(-1, 0), new XY(0, 1), new Motor(Hardware.dt_rightFront), new Motor(Hardware.dt_rightBack)));
        dtBuilder.addDriveModule(new DriveModule(new XY(1, 0), new XY(0, -1), new Motor(Hardware.dt_leftFront), new Motor(Hardware.dt_leftBack)));
        
        dtBuilder.setInput(Control.dt_input);
        dtBuilder.setDriveTrainType(DriveTrainType.TANK);

        try {
            dt = dtBuilder.build();
        } catch (InvalidDriveTrainException e) {
            e.printStackTrace();
        }
        
        correction = new GyroCorrection(Hardware.gyro, new PID(1.5, 0, 0.0015), 90, 1);

        ArrayList<Step<DTTarget>> steps = new ArrayList<>();
        lock = new GyroLock(correction);
        steps.add(new Deadzone());
        steps.add(correction);
        dt.setPipeline(new DTPipeline(steps));
        
        Control.gyroLockButton.setPressAction(new ButtonAction(){
        
            @Override
            public void onAction() {
                lock.enable();
            }
        });

        Control.gyroLockButton.setOffAction(new ButtonAction(){
            @Override
            public void onAction(){
                lock.disable();  
            }
        });

        
    }
}
