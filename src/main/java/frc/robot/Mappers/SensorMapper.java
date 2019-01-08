package frc.robot.Mappers;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.devices.CBNavXYawSource;
import org.montclairrobotics.cyborg.mappers.CBCustomMapper;

import org.montclairrobotics.cyborg.data.CBStdDriveRequestData;

public class SensorMapper extends CBCustomMapper {

    CBStdDriveRequestData stdDriveRequestData = (CBStdDriveRequestData) Cyborg.requestData.driveData;
    CBNavXYawSource navXYawSource;

    public SensorMapper(Cyborg robot) {
        super(robot);
        this.robot = robot;
    }

    @Override
    public void update() {

        stdDriveRequestData.gyroLockValue = navXYawSource.get();

    }

}