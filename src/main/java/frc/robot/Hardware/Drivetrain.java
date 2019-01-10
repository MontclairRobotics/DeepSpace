package frc.robot.Hardware;

import edu.wpi.first.wpilibj.CounterBase;
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.assemblies.CBDriveModule;
import org.montclairrobotics.cyborg.core.assemblies.CBSimpleSpeedControllerArray;
import org.montclairrobotics.cyborg.core.assemblies.CBSrxArray;
import org.montclairrobotics.cyborg.core.assemblies.CBVictorArray;
import org.montclairrobotics.cyborg.core.controllers.CBDifferentialDriveController;
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

    // Drive Data
    CBDriveControlData cbDriveControlData;

    // Declare Motors
    private CBDeviceID frontLeftMotor;
    private CBDeviceID frontRightMotor;
    private CBDeviceID backLeftMotor;
    private CBDeviceID backRightMotor;

    // Declare Encoders
    private CBDeviceID leftEncoder;
    private CBDeviceID rightEncoder;

    // Inches Per Tick For Encoders //TODO: TEST FOR VALUE
    private final double INCHES_PER_TICK = 96 / 4499;

    public Drivetrain(Cyborg cyborg,
                      CBHardwareAdapter hardwareAdapter,
                      CBDeviceID pdb,
                      CBDriveControlData cbDriveControlData){

        this.cyborg = cyborg;
        this.hardwareAdapter = hardwareAdapter;
        this.pdb = pdb;
        this.cbDriveControlData = cbDriveControlData;
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

        leftEncoder = hardwareAdapter.add(
                new CBEncoder(1, 0, CounterBase.EncodingType.k4X, false, INCHES_PER_TICK)
        );

        rightEncoder = hardwareAdapter.add(
                new CBEncoder(3, 2, CounterBase.EncodingType.k4X, false, INCHES_PER_TICK)
        );

        // // setup robot controllers
        // cyborg.addRobotController(
        //         new CBDifferentialDriveController(cyborg, cbDriveControlData)
        //                 .addLeftDriveModule(
        //                         new CBDriveModule(
        //                                 new CB2DVector(-1, 0), 0)
        //                                 .addSpeedControllerArray(
        //                                         new CBSimpleSpeedControllerArray()
        //                                                 .setDriveMode(CBEnums.CBDriveMode.Power)
        //                                                 .addSpeedController(frontLeftMotor)
        //                                                 .addSpeedController(backLeftMotor)
        //                                                 .setEncoder(leftEncoder)
        //                                                 .setErrorCorrection(
        //                                                         new CBPIDErrorCorrection()
        //                                                                 .setConstants(new double[]{1.5, 0, 0.0015}
        //                                                                 )
        //                                                 )
        //                                 )
        //                 )
        //                 .addRightDriveModule(
        //                         new CBDriveModule(new CB2DVector(1, 0), 180)
        //                                 .addSpeedControllerArray(
        //                                         new CBSimpleSpeedControllerArray()
        //                                                 .setDriveMode(CBEnums.CBDriveMode.Power)
        //                                                 .addSpeedController(frontRightMotor)
        //                                                 .addSpeedController(backRightMotor)
        //                                                 .setEncoder(rightEncoder)
        //                                                 .setErrorCorrection(
        //                                                         new CBPIDErrorCorrection()
        //                                                                 .setConstants(new double[]{1.5, 0, 0.0015}
        //                                                                 )
        //                                                 )
        //                                 )
        //                 )
        // );

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

    public CBDeviceID getLeftEncoder() {
        return leftEncoder;
    }

    public CBDeviceID getRightEncoder() {
        return rightEncoder;
    }

}
