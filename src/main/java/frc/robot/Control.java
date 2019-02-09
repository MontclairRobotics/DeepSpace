package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import org.montclairrobotics.sprocket.control.ArcadeDriveInput;
import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;
import org.opencv.core.Mat;

class Control{
    public class Port{
        public static final int DRIVE_STICK = 0;
        public static final int AUX_STICK = 0;

        public static final int GYRO_LOCK = 1;
        public static final int COMPRESSOR = 3;
        public static final int SOLENOID = 2;
        public static final int AUTO_HATCH = 1;
    }

    public static Joystick driveStick;
    public static Joystick auxStick;

    public static Button gyroLockButton;
    public static Button autoHatchButton;

    public static ArcadeDriveInput dt_input;

    public static Button compressor; 
    public static Button solenoid;


    public static void init(){
        // Drive Sticks
        driveStick = new Joystick(Port.DRIVE_STICK);
        auxStick = new Joystick(Port.AUX_STICK);


        // Control Inputs
        dt_input = new ArcadeDriveInput(driveStick);

        // Buttons
        gyroLockButton = new JoystickButton(driveStick, Port.GYRO_LOCK);
        compressor = new JoystickButton(driveStick, Port.COMPRESSOR);
        solenoid = new JoystickButton(driveStick, Port.SOLENOID);
    }
}