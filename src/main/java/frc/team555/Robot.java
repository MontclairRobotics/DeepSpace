package frc.team555;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team555.Controls.DriverControls;
import frc.team555.Hardware.Drivetrain;
import org.montclairrobotics.cyborg.CBHardwareAdapter;
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.behaviors.CBStdDriveBehavior;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBNavX;
import org.montclairrobotics.cyborg.devices.CBPDB;
import org.montclairrobotics.cyborg.mappers.CBMotorMonitorMapper;

public class Robot extends Cyborg {

    private CBDeviceID pdb, navx;

    DriverControls driverControls;
    Drivetrain drivetrain;

    @Override
    public void cyborgInit() {

        // Configure Hardware Adapter and Devices
        CBHardwareAdapter ha = new CBHardwareAdapter(this);
        hardwareAdapter = ha;

        pdb = ha.add(new CBPDB());

        navx = ha.add(new CBNavX(SPI.Port.kMXP));

        // Instantiate and Initialize
        driverControls   = new DriverControls(this, ha);
        drivetrain       = new Drivetrain(this, ha, pdb);

        // Run Setups functions
        SmartDashboard.putBoolean("Driver Control Setup",driverControls.setup());
        SmartDashboard.putBoolean("Drive Train Setup",drivetrain.setup());

        // Add CB Monitor Mapper
        this.addCustomMapper(
                new CBMotorMonitorMapper(this)
                        .add(drivetrain.getFrontLeftMotor())
                        .add(drivetrain.getFrontRightMotor())
                        .add(drivetrain.getBackLeftMotor())
                        .add(drivetrain.getBackRightMotor())
        );

        // Setup Behaviors
        this.addBehavior(new CBStdDriveBehavior(this));

    }

    @Override
    public void cyborgTeleopInit() {

    }

    @Override
    public void cyborgTestInit() {

    }

    @Override
    public void cyborgTestPeriodic() {

    }
}
