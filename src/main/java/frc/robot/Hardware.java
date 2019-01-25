package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import org.montclairrobotics.sprocket.control.ArcadeDriveInput;

public class Hardware {
    
    public static class DeviceID {
        // Drive Train ID's
        public static final int DRIVE_RIGHT_FRONT = 2; // Forwards
        public static final int DRIVE_RIGHT_BACK = 1;  // Forwards
        public static final int DRIVE_LEFT_FRONT = 3;  // Backwards
        public static final int DRIVE_LEFT_BACK = 0;   // Backwards

        public static final SPI.Port navxPort = SPI.Port.kMXP;
    }
    


    public static SP dt_rightFront;
    public static SP dt_rightBack;
    public static SP dt_leftFront;
    public static SP dt_leftBack;



    public static NavXInput gyro;

    public static void init(){
        System.out.println("Initializing Hardware");

        dt_rightFront = new SP(new VictorSP(DeviceID.DRIVE_RIGHT_FRONT));
        dt_rightBack =  new SP(new VictorSP(DeviceID.DRIVE_RIGHT_BACK));
        dt_leftFront =  new SP(new VictorSP(DeviceID.DRIVE_LEFT_FRONT));
        dt_leftBack =   new SP(new VictorSP(DeviceID.DRIVE_LEFT_BACK));

        gyro = new NavXInput(DeviceID.navxPort);
    }

}

