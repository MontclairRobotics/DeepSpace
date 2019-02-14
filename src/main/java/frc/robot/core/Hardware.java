package frc.robot.core;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.utils.SP;
import frc.robot.utils.NavXInput;

/**
 * The hardware class is in charge of storing all hardware
 * devices and configurations that the 2019 DeepSpace robot
 * will use. The configurations are based on the google sheet
 * that can be found Here: <link></link>
 * The sheet is in place so that both code and electronics are
 * on the same page from the start on what the device configurations
 * are.
 *
 * Hardware device: Any physical device on the robot that is connected to the electronics
 * board or on roborio. This includes motors, cameras, servos, etc.
 *
 * Sensors and inputs should be defined in the control class and not in hardware
 * @see Control
 *
 * Structure
 *
 * - Device Port configuration: All port ID's for hardware devices.
 *      - Drive Train Motor ID's: motor ports to be used for the drive train
 * - Motor Configuration: Declaration of all motor controllers on the robot
 *      - Drive Train Motors: Declaration of the drive train motors
 *
 */
public class Hardware {
    
    public static class DeviceID {
        // Drive Train ID's
        public static final int DRIVE_RIGHT_FRONT = 1; // Forwards
        public static final int DRIVE_RIGHT_BACK = 13;  // Forwards
        public static final int DRIVE_LEFT_FRONT = 0;  // Backwards
        public static final int DRIVE_LEFT_BACK = 14;   // Backwards

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

