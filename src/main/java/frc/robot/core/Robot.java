
package frc.robot.core;

import java.util.ArrayList;

import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.utils.FieldCentric;
import frc.robot.utils.PressureRegulator;
import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.auto.AutoMode;
import org.montclairrobotics.sprocket.control.DashboardInput;
import frc.robot.components.Intake;
import frc.robot.components.Lift;
import frc.robot.utils.*;
import org.montclairrobotics.sprocket.control.ToggleButton;
import org.montclairrobotics.sprocket.drive.*;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.drive.steps.Sensitivity;
import org.montclairrobotics.sprocket.drive.utils.GyroLock;
import org.montclairrobotics.sprocket.geometry.Degrees;
import org.montclairrobotics.sprocket.geometry.Polar;
import org.montclairrobotics.sprocket.geometry.XY;
import org.montclairrobotics.sprocket.motors.Module;
import org.montclairrobotics.sprocket.motors.Motor;
import org.montclairrobotics.sprocket.motors.SEncoder;
import org.montclairrobotics.sprocket.pipeline.Step;
import org.montclairrobotics.sprocket.utils.CameraServers;
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

    // Drive Train Steps
    GyroCorrection correction;
    GyroLock lock;
    FieldCentric fieldCentric;
    Sensitivity sensitivity;
    VisionCorrection visionCorrection;
    Orientation orientation;

    // Mechanisms
    Lift lift;
    Intake intake;

    Compressor compressor;
    SSolenoid solenoid;

    LimitSwitch mainLimit;
    LimitSwitch secondLimit;

    @Override
    public void robotInit(){
        // Initialization
        Hardware.init();
        Control.init();
        // CameraServer.getInstance().startAutomaticCapture();

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
        orientation = new Orientation(correction);
        sensitivity = new Sensitivity(0.3);
        correction.reset();

        // Add drive train steps
        ArrayList<Step<DTTarget>> steps = new ArrayList<>();
        visionCorrection = new VisionCorrection(new DashboardInput("hatchX"), new PID(1, 0, 0));
        visionCorrection.setTarget(200); // TODO: Test and tune
        new ToggleButton(Control.driveStick, Control.Port.AUTO_HATCH, visionCorrection);
        // steps.add(visionCorrection);
        // steps.add(new VisionCorrection(new VisionTarget(CameraServer.getInstance().getVideo()), new PID(.1, 0, 0)));
        steps.add(correction);
        steps.add(fieldCentric);
        steps.add(orientation);
        steps.add(sensitivity);
        driveTrain.setPipeline(new DTPipeline(steps));

        // Pneumatics
        compressor = new Compressor(20);
        solenoid = new SSolenoid(new Solenoid(20, 3));
        PressureRegulator p = new PressureRegulator(compressor);
        p.enable();

        mainLimit = new LimitSwitch(9, true);
        secondLimit = new LimitSwitch(8, true);
        // Lift
        lift = new Lift(Control.AUX_RIGHT_Y_AXIS, Control.liftUp, Control.liftDown, new Module(
                Hardware.lift_encoder, // Todo: Ticks Per inch
                new PID(.5, .001, .01),
                Module.MotorInputType.PERCENT,
                // new LimitedMotor(Hardware.lift_1, mainLimit, () -> Hardware.lift_encoder.getTicks() > 10000000),
                new LimitedMotor(Hardware.lift_2, mainLimit, () -> Hardware.lift_encoder.getTicks() > 10000000),
                new LimitedMotor(Hardware.lift_3, secondLimit, () -> Hardware.second_lift_encoder.getTicks() >  330000.0)
        ));

        // Intake
        intake = new Intake(
                Control.AUX_LEFT_Y_AXIS,
                new SplitButton(
                        Control.intakeUp,
                        Control.intakeDown
                ),
                Control.ballFire, new Module(
                    null,
                    null,
                    Module.MotorInputType.PERCENT,
                    new Motor(Hardware.intake_left),
                    new Motor(Hardware.intake_right)
                ),
                new Motor(Hardware.intake_rotate)
        );


        // BUTTONS
        ToggleButton fieldCentricButton = new ToggleButton(Control.driveStick, Control.Port.FIELD_CENTRIC, fieldCentric);
        ToggleButton gyroLockButton = new ToggleButton(Control.driveStick, Control.Port.GYRO_LOCK, lock);
        ToggleButton solenoidButton = new ToggleButton(Control.auxStick, Control.Port.SOLENOID, solenoid);

        // addAutoMode(new AutoMode("Path test", new PathState("Test"), new TeleopState(this)));

    }

    @Override
    public void update() {
        // Debug information
        Debug.msg("Pressure Switch Valve", compressor.getPressureSwitchValue());
        Debug.msg("Compressor Current", compressor.getCompressorCurrent());

        mainLimit.get();
        secondLimit.get();
        Debug.msg("zero lift encoder", Hardware.t_encoder.getTicks());
        Debug.msg("second lift encoder", Hardware.second_lift_encoder.getTicks());
        Debug.msg("Lift Encoder", Hardware.lift_encoder.getTicks());
        Debug.msg("Lift Diff", Hardware.t_encoder.getTicks()-Math.abs(Hardware.lift_encoder.getTicks()));
        if(secondLimit.get()){
            Hardware.second_lift_encoder.reset();
        }
        if(mainLimit.get()){
            Hardware.lift_encoder.reset();
            Hardware.t_encoder.reset();
        }
    }
}
