package frc.robot.core;

import frc.robot.utils.MecanumInput;
import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.JoystickButton;

import edu.wpi.first.wpilibj.Joystick;
import org.montclairrobotics.sprocket.utils.Input;

/**
 * The Control class is in charge of storing all of the port
 * configurations and control elements for the 2019 DeepSpace robot.
 *
 * A control element is roughly defined as something that's only
 * use is as an input, that directly affects another part of the code
 * or the robot.
 *
 * This includes things like: Buttons, Joysticks, Sensors, etc.
 *
 * The only thing that goes against this rule, are motor encoders,
 * as they are seen to be a part of the motor hardware, and therefore
 * go into the hardware class
 *
 * @see Hardware
 *
 * Structure - This defines where and in what order to declare objects and port ID's
 *
 * Hierarchy - Always define in order from first to last
 *  - Drive Sticks
 *  - Drive Modifiers
 *  - Buttons
 *  - Misc
 *
 *  Sections - Order and sections of declaration and instantiation
 *  - Declaration of all ports
 *  - Creation of all control objects
 *  - Instantiation of all control objects

 */
public class Control{
    public class Port{
        public static final int DRIVE_STICK = 0;
        public static final int AUX_STICK = 1;

        //Drive Stick
        public static final int GYRO_LOCK = 1;
        public static final int SOLENOID = 2;
        public static final int COMPRESSOR = 3;
        public static final int AUTO_HATCH = 4;
        public static final int FIELD_CENTRIC = 8;

    }

    public static Joystick driveStick;
    public static Joystick auxStick;

    public static Button gyroLockButton;

    public static MecanumInput dt_input;

    public static Button compressor; 
    public static Button solenoid;

    public static void init(){
        driveStick = new Joystick(Port.DRIVE_STICK);
        auxStick = new Joystick(Port.AUX_STICK);

        dt_input = new MecanumInput(driveStick, () -> -driveStick.getRawAxis(2));

        gyroLockButton = new JoystickButton(driveStick, Port.GYRO_LOCK);
        compressor = new JoystickButton(driveStick, Port.COMPRESSOR);
        solenoid = new JoystickButton(driveStick, Port.SOLENOID);
    }
}