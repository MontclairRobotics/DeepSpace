package frc.robot.cyborg.Hardware;

import edu.wpi.first.wpilibj.CounterBase;
import frc.robot.cyborg.Data.ControlData;
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.assemblies.CBDriveModule;
import org.montclairrobotics.cyborg.core.assemblies.CBSimpleSpeedControllerArray;
import org.montclairrobotics.cyborg.core.controllers.CBDifferentialDriveController;
import org.montclairrobotics.cyborg.core.controllers.CBMecanumDriveController;
import org.montclairrobotics.cyborg.core.data.CBDriveControlData;
import org.montclairrobotics.cyborg.core.utils.CB2DVector;
import org.montclairrobotics.cyborg.core.utils.CBEnums;
import org.montclairrobotics.cyborg.core.utils.CBPIDErrorCorrection;
import org.montclairrobotics.cyborg.devices.*;

public class Drivetrain {

    // Declare Cyborg Robot Class
    private Cyborg cyborg;

    // Declare Cyborg Hardware Adapter
    private CBHardwareAdapter hardwareAdapter;

    // Declare Power Distribution Board
    private CBDeviceID pdb;

    // Control Data
    ControlData controlData;

    // Declare Motors
    private CBDeviceID frontLeftMotor;
    private CBDeviceID frontRightMotor;
    private CBDeviceID backLeftMotor;
    private CBDeviceID backRightMotor;

    // Declare Encoders
    private CBDeviceID frontLeftEncoder;
    private CBDeviceID frontRightEncoder;
    private CBDeviceID backLeftEncoder;
    private CBDeviceID backRightEncoder;

    // Inches Per Tick For Encoders //TODO: TEST FOR VALUE
    private final double INCHES_PER_TICK = 96 / 4499;

    public Drivetrain(Cyborg cyborg,
                      CBHardwareAdapter hardwareAdapter,
                      CBDeviceID pdb){

        this.cyborg = cyborg;
        this.hardwareAdapter = hardwareAdapter;
        this.pdb = pdb;
    }

    public boolean setup(){

        frontLeftMotor = hardwareAdapter.add(
                new CBTalonSRX(4)
                        .setDeviceName("DriveTrain", "frontLeft")
                        .setPowerSource(pdb, 4)
                        .setSpeedControllerFaultCriteria(
                                new CBSpeedControllerFaultCriteria()
                                        .setBasic(.1, 1, 30)
                        )
        );

        frontRightMotor = hardwareAdapter.add(
                new CBTalonSRX(1)
                        .setDeviceName("DriveTrain", "frontRight")
                        .setPowerSource(pdb, 1)
                        .setSpeedControllerFaultCriteria(
                                new CBSpeedControllerFaultCriteria()
                                        .setBasic(.1, 1, 30)
                        )
        );

        backLeftMotor = hardwareAdapter.add(
                new CBTalonSRX(2)
                        .setDeviceName("DriveTrain", "backLeft")
                        .setPowerSource(pdb, 3)
                        .setSpeedControllerFaultCriteria(
                                new CBSpeedControllerFaultCriteria()
                                        .setBasic(.1, 1, 30)
                        )
        );

        backRightMotor = hardwareAdapter.add(
                new CBTalonSRX(3)
                        .setDeviceName("DriveTrain", "backRight")
                        .setPowerSource(pdb, 2)
                        .setSpeedControllerFaultCriteria(
                                new CBSpeedControllerFaultCriteria()
                                        .setBasic(.1, 1, 30)
                        )
        );

        frontLeftEncoder = hardwareAdapter.add(
                new CBEncoder(1, 0, CounterBase.EncodingType.k4X, false, INCHES_PER_TICK)
        );

        frontRightEncoder = hardwareAdapter.add(
                new CBEncoder(3, 2, CounterBase.EncodingType.k4X, false, INCHES_PER_TICK)
        );

        backLeftEncoder = hardwareAdapter.add(
                new CBEncoder(4,5, CounterBase.EncodingType.k4X,false,INCHES_PER_TICK)
        );

        backRightEncoder = hardwareAdapter.add(
                new CBEncoder(4,5, CounterBase.EncodingType.k4X,false,INCHES_PER_TICK)
        );

         // setup robot controllers
         cyborg.addRobotController(
                 new CBMecanumDriveController(cyborg,controlData.drivetrain)
                         .addDriveModule(
                                 new CBDriveModule(
                                         new CB2DVector(-1, 1), 0)
                                         .addSpeedControllerArray(
                                                 new CBSimpleSpeedControllerArray()
                                                         .setDriveMode(CBEnums.CBDriveMode.Power)
                                                         .addSpeedController(frontLeftMotor)
                                                         .setEncoder(frontLeftEncoder)
                                         )
                         )

                         .addDriveModule(
                                 new CBDriveModule(
                                         new CB2DVector(1, 1), 0)
                                         .addSpeedControllerArray(
                                                 new CBSimpleSpeedControllerArray()
                                                         .setDriveMode(CBEnums.CBDriveMode.Power)
                                                         .addSpeedController(frontRightMotor)
                                                         .setEncoder(frontRightEncoder)

                                         )
                         )

                         .addDriveModule(
                                 new CBDriveModule(
                                         new CB2DVector(-1, -1), 0)
                                         .addSpeedControllerArray(
                                                 new CBSimpleSpeedControllerArray()
                                                         .setDriveMode(CBEnums.CBDriveMode.Power)
                                                         .addSpeedController(backLeftMotor)
                                                         .setEncoder(backLeftEncoder)

                                         )
                         )

                         .addDriveModule(
                                 new CBDriveModule(
                                         new CB2DVector(1, -1), 0)
                                         .addSpeedControllerArray(
                                                 new CBSimpleSpeedControllerArray()
                                                         .setDriveMode(CBEnums.CBDriveMode.Power)
                                                         .addSpeedController(backRightMotor)
                                                         .setEncoder(backRightEncoder)

                                         )
                 )


         );

        return true;
    }

    public CBDeviceID getFrontLeftMotor() {
        return frontLeftMotor;
    }

    public CBDeviceID getFrontRightMotor() {
        return frontRightMotor;
    }

    public CBDeviceID getBackLeftMotor() {
        return backLeftMotor;
    }

    public CBDeviceID getBackRightMotor() {
        return backRightMotor;
    }

    public CBDeviceID getFrontLeftEncoder() {
        return frontLeftEncoder;
    }

    public CBDeviceID getFrontRightEncoder() {
        return frontRightEncoder;
    }

    public CBDeviceID getBackLeftEncoder() {
        return backLeftEncoder;
    }

    public CBDeviceID getBackRightEncoder() {
        return backRightEncoder;
    }
}
