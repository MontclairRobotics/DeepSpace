package frc.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Controls.DriverControls;
import frc.robot.Data.ControlData;
import frc.robot.Data.RequestData;
import frc.robot.Hardware.Drivetrain;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.behaviors.CBStdDriveBehavior;
import org.montclairrobotics.cyborg.core.mappers.CBMotorMonitorMapper;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBHardwareAdapter;
import org.montclairrobotics.cyborg.devices.CBNavX;
import org.montclairrobotics.cyborg.devices.CBPDB;

public class Robot extends Cyborg {

    private CBDeviceID pdb, navx;

    public static RequestData requestData;
    public static ControlData controlData;


    DriverControls driverControls;
    Drivetrain drivetrain;

    @Override
    public void cyborgInit() {

        // Data init
        requestData = new RequestData();
        controlData = new ControlData();

        // Configure Hardware Adapter and Devices
        CBHardwareAdapter ha = new CBHardwareAdapter(this);
        hardwareAdapter = ha;

        pdb = ha.add(new CBPDB());

        navx = ha.add(new CBNavX(SPI.Port.kMXP));

        // Instantiate and Initialize
        driverControls   = new DriverControls(this, ha, requestData.drivetrain);
        drivetrain       = new Drivetrain(this, ha, pdb, controlData.drivetrain);

        // Run Setups functions
        SmartDashboard.putBoolean("Driver Control Setup",driverControls.setup());
        SmartDashboard.putBoolean("Drive Train Setup",drivetrain.setup());

        // Add CB Monitor Mapper
        this.addSensorMapper(
            new CBMotorMonitorMapper(this)
                .add(drivetrain.getFrontLeftMotor())
                .add(drivetrain.getFrontRightMotor())
                .add(drivetrain.getBackLeftMotor())                       
                .add(drivetrain.getBackRightMotor())
        );


        // Setup Behaviors
        this.addBehavior(new CBStdDriveBehavior(this,
                requestData.drivetrain,
                controlData.drivetrain)
        );

    }

    @Override
    public void cyborgDisabledInit() {

    }

    @Override
    public void cyborgAutonomousInit() {

    }

    @Override
    public void cyborgTeleopInit() {

    }

    @Override
    public void cyborgTestInit() {

    }

}
