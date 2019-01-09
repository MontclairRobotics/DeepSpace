package frc.robot.Mappers;

import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.data.CBStdDriveControlData;
import org.montclairrobotics.cyborg.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.devices.CBDashboardChooser;
import org.montclairrobotics.cyborg.devices.CBDeviceID;
import org.montclairrobotics.cyborg.devices.CBEncoder;
import org.montclairrobotics.cyborg.devices.CBNavXYawSource;
import org.montclairrobotics.cyborg.mappers.CBCustomMapper;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Data.RequestData;

public class SensorMapper extends CBCustomMapper {

    Cyborg cyborg;
    
    CBStdDriveRequestData stdDriveRequestData = (CBStdDriveRequestData) Cyborg.requestData.driveData;

    RequestData requestData = (RequestData) Cyborg.requestData;


	CBDashboardChooser<Integer> autoChooser;
    CBDashboardChooser<Integer> autoSide;

	CBNavXYawSource navxYawSource;
	CBEncoder leftEncoder;
	CBEncoder rightEncoder;

	public SensorMapper(Cyborg cyborg) {
		super(cyborg);
		this.cyborg = cyborg;	
	}

	@Override
	public void update() {

		if(autoChooser!=null){
            requestData.selectedAuto = autoChooser.getSelected();
        }
		
		if(autoSide!=null){
            requestData.selectedSide = autoSide.getSelected();
        }
		
		stdDriveRequestData.gyroLockValue = navxYawSource.get();

		if(leftEncoder!=null) {
			requestData.leftDriveEncoder = leftEncoder.get();
			requestData.rightDriveEncoder = rightEncoder.get();
            requestData.leftDriveEncoderSpeed = leftEncoder.getRate();
			requestData.rightDriveEncoderSpeed = rightEncoder.getRate();
			SmartDashboard.putNumber("LeftEncoderGet:", requestData.leftDriveEncoder);
			SmartDashboard.putNumber("RightEncoderGet:", requestData.rightDriveEncoder);
			SmartDashboard.putNumber("LeftEncoderSpeed:", requestData.leftDriveEncoderSpeed);
			SmartDashboard.putNumber("RightEncoderSpeed:", requestData.rightDriveEncoderSpeed);
		}
	}

	public SensorMapper setGyroLockSource(CBDeviceID navxId) {
		navxYawSource = new CBNavXYawSource(navxId); 
		return this;
	}

	@SuppressWarnings("unchecked")
	public SensorMapper setAutoChooser(CBDeviceID chooserId) {
		this.autoChooser = (CBDashboardChooser<Integer>)Cyborg.hardwareAdapter.getDevice(chooserId);
		return this;
	}

	@SuppressWarnings("unchecked")
	public SensorMapper setAutoSide(CBDeviceID chooserId) {
		this.autoSide = (CBDashboardChooser<Integer>)Cyborg.hardwareAdapter.getDevice(chooserId);
		return this;
	}

	public SensorMapper setDriveEncoders(CBDeviceID leftEncoder, CBDeviceID rightEncoder) {
		this.leftEncoder = Cyborg.hardwareAdapter.getEncoder(leftEncoder);
		this.rightEncoder = Cyborg.hardwareAdapter.getEncoder(rightEncoder);
		return this;
	}

}