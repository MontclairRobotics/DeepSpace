
package frc.robot.core;

import java.util.ArrayList;

import frc.robot.utils.FieldCentric;
import frc.robot.utils.Pathweaver;
import frc.robot.utils.PressureRegulator;
import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.control.ToggleButton;
import org.montclairrobotics.sprocket.drive.*;
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

/**
 * The main robot class for Montclair Robotic's 2019 Deep Space Robot
 *
 * This class is a sub-class of SprocketRobot, which means that this
 * robot uses the Sprocket Framework.
 *
 * The Sprocket Framework requires the user to define all information about
 * features and mechanisms in the main robot class, and then it takes care of
 * the rest in it's own regulated Update loop, although a user implemented
 * update loop is contained in this class, under the update() method, it is
 * only for debugging, and should not be used to update any Sprocket components
 * as it may cause problems.
 *
 * This class is organized in the following way
 *
 * Declaration - A declaration of all pertinent robot objects
 * Initialization -  Initializes static Hardware and Control Objects
 * Drive Train Setup - Defines the Drive Modules
 * Drive Train Building - Builds the drive train and adds control steps
 * Button Definition - Defining
 *
 */
public class Robot extends SprocketRobot {

    DriveTrain driveTrain;

    GyroCorrection correction;
    GyroLock lock;
    FieldCentric fieldCentric;
    Sensitivity sensitivity;

    Compressor compressor;
    Solenoid solenoid;

    @Override
    public void robotInit(){
        // Initialization
        Hardware.init();
        Control.init();


        // Drivetrain code
        DriveTrainBuilder dtBuilder = new DriveTrainBuilder();
        // Right
        dtBuilder.addDriveModule(new DriveModule(new XY(1, 1), new Polar(-1, new Degrees(-45)), new Motor(Hardware.dt_rightFront)));
        dtBuilder.addDriveModule(new DriveModule(new XY(1, -1), new Polar(-1, new Degrees(-45)), new Motor(Hardware.dt_rightBack)));
        // Left
        dtBuilder.addDriveModule(new DriveModule(new XY(-1, 1), new Polar(1, new Degrees(45)), new Motor(Hardware.dt_leftFront)));
        dtBuilder.addDriveModule(new DriveModule(new XY(-1, -1), new Polar(1, new Degrees(45)), new Motor(Hardware.dt_leftBack)));



        dtBuilder.setInput(Control.dt_input);
        dtBuilder.setDriveTrainType(DriveTrainType.MECANUM);

        try {
            driveTrain = dtBuilder.build();
            driveTrain.setMapper(new MecanumMapper());
        } catch (InvalidDriveTrainException e) {
            e.printStackTrace();
        }

        // Create drive train steps
        correction = new GyroCorrection(Hardware.gyro, new PID(-0.3, 0, -0.00035), 90, 1);
        fieldCentric = new FieldCentric(correction);
        lock = new GyroLock(correction);
        sensitivity = new Sensitivity(0.3);
        correction.reset();

        // Add drive train steps
        ArrayList<Step<DTTarget>> steps = new ArrayList<>();
        steps.add(correction);
        steps.add(fieldCentric);
        steps.add(sensitivity);
        driveTrain.setPipeline(new DTPipeline(steps));

        // Pneumatics
        compressor = new Compressor(0);
        solenoid = new Solenoid(3);
        PressureRegulator p = new PressureRegulator(compressor);
        p.enable();


        // BUTTONS
        ToggleButton fieldCentricButton = new ToggleButton(Control.driveStick, Control.Port.FIELD_CENTRIC, fieldCentric);

        Control.solenoid.setPressAction(new ButtonAction(){
            @Override
            public void onAction(){
                solenoid.set(false);
            }
        });
        Control.solenoid.setOffAction(new ButtonAction(){
            @Override
            public void onAction(){
                solenoid.set(true);
            }
        });

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

    @Override
    public void update() {
        // Debug information
        Debug.msg("Pressure Switch Valve", compressor.getPressureSwitchValue());
        Debug.msg("Comrpessor Current", compressor.getCompressorCurrent());
    }
}
