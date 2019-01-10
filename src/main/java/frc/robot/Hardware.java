package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Hardware {
    
    public class DeviceID {
        // Drive Train ID's
        public static final int DRIVE_RIGHT_FRONT = 1;
        public static final int DRIVE_RIGHT_BACK = 2;
        public static final int DRIVE_LEFT_FRONT = 4;
        public static final int DRIVE_LEFT_BACK = 3;
    }
    
    public static WPI_TalonSRX dt_rightFront;
    public static WPI_TalonSRX dt_rightBack;
    public static WPI_TalonSRX dt_leftFront;
    public static WPI_TalonSRX dt_leftBack;
    
    public static void init(){
        dt_rightFront = new WPI_TalonSRX(DeviceID.DRIVE_RIGHT_FRONT);
        dt_rightBack = new WPI_TalonSRX(DeviceID.DRIVE_RIGHT_BACK);
        dt_rightFront = new WPI_TalonSRX(DeviceID.DRIVE_LEFT_FRONT);
        dt_rightBack = new WPI_TalonSRX(DeviceID.DRIVE_LEFT_BACK);
    }
}

