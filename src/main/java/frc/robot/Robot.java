
package frc.robot;

import java.util.ArrayList;

import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.control.ToggleButton;
import org.montclairrobotics.sprocket.drive.*;
import org.montclairrobotics.sprocket.drive.steps.Deadzone;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.drive.steps.Sensitivity;
import org.montclairrobotics.sprocket.drive.utils.GyroLock;
import org.montclairrobotics.sprocket.geometry.Degrees;
import org.montclairrobotics.sprocket.geometry.Polar;
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
        // Right
        dtBuilder.addDriveModule(new DriveModule(new XY(1, 1), new Polar(-1, new Degrees(-45)), new Motor(Hardware.dt_rightFront)));
        dtBuilder.addDriveModule(new DriveModule(new XY(1, -1), new Polar(-1, new Degrees(-45)), new Motor(Hardware.dt_rightBack)));
        // Left
        dtBuilder.addDriveModule(new DriveModule(new XY(-1, 1), new Polar(1, new Degrees(45)), new Motor(Hardware.dt_leftFront)));
        dtBuilder.addDriveModule(new DriveModule(new XY(-1, -1), new Polar(1, new Degrees(45)), new Motor(Hardware.dt_leftBack)));


        dtBuilder.setInput(Control.dt_input);
        dtBuilder.setDriveTrainType(DriveTrainType.TANK);

        try {
            dt = dtBuilder.build();
             dt.setMapper(new MecanumMapper());
        } catch (InvalidDriveTrainException e) {
            e.printStackTrace();
        }
        
        correction = new GyroCorrection(Hardware.gyro, new PID(-0.3, 0, -0.00035), 90, 1);

        ArrayList<Step<DTTarget>> steps = new ArrayList<>();
        lock = new GyroLock(correction);
        steps.add(correction);
        FieldCentric fieldCentric = new FieldCentric(correction);
        steps.add(fieldCentric);
        correction.reset();
        steps.add(new Sensitivity(.3));
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

        Compressor c = new Compressor(0);
        Control.compressor.setPressAction(new ButtonAction(){
            @Override
            public void onAction(){
                c.setClosedLoopControl(true);
            }
        });

        Control.compressor.setOffAction(new ButtonAction(){
            @Override
            public void onAction(){
                c.setClosedLoopControl(false);
            }
        });
        // PressureRegulator p = new PressureRegulator(c);
        // p.enable();
        ToggleButton fieldCentricButton = new ToggleButton(Control.driveStick, 8, fieldCentric);
        Debug.msg("Pressure Switch Valve", c.getPressureSwitchValue());
        Debug.msg("Comrpessor Current", c.getCompressorCurrent());

        Solenoid s = new Solenoid(3);
        Control.solenoid.setPressAction(new ButtonAction(){
            @Override
            public void onAction(){
                s.set(false);
            }
        });
        Control.solenoid.setOffAction(new ButtonAction(){
            @Override
            public void onAction(){
                s.set(true);
            }
        });
    }
}
