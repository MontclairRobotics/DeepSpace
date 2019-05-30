package frc.robot.test;

import frc.robot.core.Control;
import frc.robot.core.Hardware;
import frc.robot.utils.FieldCentric;
import frc.robot.utils.PathFollower;
import frc.robot.utils.Pathweaver;
import jaci.pathfinder.PathfinderFRC;
import org.montclairrobotics.sprocket.SprocketRobot;
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
import org.montclairrobotics.sprocket.utils.PID;

import java.util.ArrayList;

public class PathTest extends SprocketRobot {
    DriveTrain driveTrain;

    GyroCorrection correction;
    GyroLock lock;
    FieldCentric fieldCentric;
    Sensitivity sensitivity;
    PathFollower pathFollower;

    @Override
    public void robotInit() {
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
        pathFollower = new PathFollower(PathfinderFRC.getTrajectory("Test"), correction);

        // Add drive train steps
        ArrayList<Step<DTTarget>> steps = new ArrayList<>();
        steps.add(correction);
        steps.add(fieldCentric);
        steps.add(sensitivity);
        steps.add(pathFollower);
        driveTrain.setPipeline(new DTPipeline(steps));

        ToggleButton followPath = new ToggleButton(Control.driveStick, 1, pathFollower);
    }
}
