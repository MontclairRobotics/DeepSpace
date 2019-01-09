package frc.robot.Data;

import org.montclairrobotics.cyborg.data.CBRequestData;

public class RequestData extends CBRequestData{
    
   // Sensor Mapper Output
	public int selectedAuto = -1;
	public int selectedSide = -1;
	public double targetX = -1;
	public double targetY = -1;
	
	public boolean pidTuneEnable = false;
	public boolean pidTuneCycle = false;
	
	public double leftDriveEncoder;
	public double rightDriveEncoder;
	public double leftDriveEncoderSpeed;
	public double rightDriveEncoderSpeed;
	public boolean rightDriveEncoderDir;
	public boolean leftDriveEncoderDir;
    
}