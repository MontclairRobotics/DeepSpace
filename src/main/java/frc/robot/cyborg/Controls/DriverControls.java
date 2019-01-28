package frc.robot.cyborg.Controls;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.core.mappers.CBArcadeDriveMapper;
import org.montclairrobotics.cyborg.devices.CBAxis;
import org.montclairrobotics.cyborg.devices.CBButton;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBHardwareAdapter;

public class DriverControls {

    //Cyborg Robot Class
    private Cyborg cyborg;

    // Cyborg Hardware Adapter
    private CBHardwareAdapter hardwareAdapter;

    CBStdDriveRequestData cbStdDriveRequestData;

    // Joystick USB Port
    private final int STICK_ID = 0;

    // Declare Joystick axis and buttons
    private CBDeviceID xAxis;
    private CBDeviceID yAxis;
    private CBDeviceID gyroLockButton;

    public DriverControls(Cyborg cyborg,
                          CBHardwareAdapter hardwareAdapter,
                          CBStdDriveRequestData cbStdDriveRequestData){
        this.cyborg = cyborg;
        this.hardwareAdapter = hardwareAdapter;
        this.cbStdDriveRequestData = cbStdDriveRequestData;

    }

    public boolean setup() {

        //Initialize Axis
        xAxis = hardwareAdapter.add(
                new CBAxis(STICK_ID, 1)
                        .setDeadzone(0.1)
//                        .setScale(-1.0)
        );

        yAxis = hardwareAdapter.add(
                new CBAxis(STICK_ID, 0)
                        .setDeadzone(0.1)
//                        .setScale(-1.0)
        );

        //Initialize Buttons
        gyroLockButton = hardwareAdapter.add(
                new CBButton(STICK_ID, 1)
        );

        // Setup Drive Mapper
        cyborg.addTeleOpMapper(
                new CBArcadeDriveMapper(cyborg, cbStdDriveRequestData)
                        .setAxes(xAxis, null, yAxis)
                        // TODO: the following line commented because the mode above was changed to Power (from speed)
                        // TODO: Tune Axis Scales
                        //.setAxisScales(0, 40, 90) // no strafe, 40 inches/second, 90 degrees/second
                        .setGyroLockButton(gyroLockButton)
        );

        return true;
    }

}