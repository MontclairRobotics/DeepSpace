package frc.robot.cyborg.Mapper;

import frc.robot.cyborg.Data.MecanumDriveRequestData;
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.core.data.CBDriveRequestData;
import org.montclairrobotics.cyborg.core.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.core.mappers.CBTeleOpMapper;
import org.montclairrobotics.cyborg.devices.CBAxis;
import org.montclairrobotics.cyborg.devices.CBButton;
import org.montclairrobotics.cyborg.devices.CBDeviceID;

public class MecanumMapper extends CBTeleOpMapper {

    private CBAxis forward;
    private CBAxis turn;
    private CBAxis strafe;

    private double deadzone = 0.0;
    private CBButton gyroLock = null;
    private CBStdDriveRequestData sdrd;
    private MecanumDriveRequestData mdrd;

    public MecanumMapper(Cyborg robot,
                         CBDriveRequestData requestData,
                         CBDeviceID forwardDeviceID,
                         CBDeviceID turnDeviceID,
                         CBDeviceID strafeDeviceID) {
        super(robot);
        setRequestData(requestData);
        this.forward = Cyborg.hardwareAdapter.getAxis(forwardDeviceID);
        this.turn = Cyborg.hardwareAdapter.getAxis(turnDeviceID);
        this.strafe = Cyborg.hardwareAdapter.getAxis(strafeDeviceID);
    }

    private MecanumMapper setRequestData(CBDriveRequestData data) {
        sdrd = null;
        mdrd = null;
        if (data instanceof CBStdDriveRequestData) {
            sdrd = (CBStdDriveRequestData) data;
        } else if (data instanceof MecanumDriveRequestData) {
            mdrd = (MecanumDriveRequestData) data;
        } else {
            data.active = false; // If we don't know what type of request it is shut down drive
            throw new RuntimeException("Unknown driveRequestData type in MecanumDriveMapper.");
        }
        return this;
    }

    public MecanumMapper setDeadZone(double deadzone) {
        this.deadzone = deadzone;
        return this;
    }

    public MecanumMapper setGyroLockButton(CBDeviceID buttonID) {
        this.gyroLock = Cyborg.hardwareAdapter.getButton(buttonID);
        return this;
    }


    @Override
    public void init() {

    }

    @Override
    public void update() {
        //TODO
    }
}
