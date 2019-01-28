package frc.robot.cyborg.Data;

import org.montclairrobotics.cyborg.core.data.CBRequestData;
import org.montclairrobotics.cyborg.core.data.CBStdDriveRequestData;

public class RequestData extends CBRequestData {
    
   public double robotAngle;

   public CBStdDriveRequestData drivetrain = new CBStdDriveRequestData();

   public RequestData(){

   }
    
}