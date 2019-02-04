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

        public static final int GYRO_LOCK = 12;
        public static final int FIELD_CENTRIC = 6;

        public static final int SOLENOID = 7;

        public static final int BALL_FIRE = 7;

        public static final int LIFT_DOWN = 1;
        public static final int LIFT_UP = 4;
    }

    public static Joystick driveStick;
    public static Joystick auxStick;

    public static Button gyroLockButton;

    public static MecanumInput dt_input;

    public static Button solenoid;
    public static Button ballFire;
    public static Button liftUp;
    public static Button liftDown;


    public static Input<Double> DRIVE_RIGHT_X_AXIS;
    public static Input<Double> DRIVE_RIGHT_Y_AXIS;
    public static Input<Double> DRIVE_LEFT_X_AXIS;
    public static Input<Double> DRIVE_LEFT_Y_AXIS;

    public static Input<Double> AUX_RIGHT_X_AXIS;
    public static Input<Double> AUX_RIGHT_Y_AXIS;
    public static Input<Double> AUX_LEFT_X_AXIS;
    public static Input<Double> AUX_LEFT_Y_AXIS;

    public static void init(){
        driveStick = new Joystick(Port.DRIVE_STICK);
        auxStick = new Joystick(Port.AUX_STICK);
        driveStick.getPOV();

        dt_input = new MecanumInput(driveStick, () -> -driveStick.getRawAxis(2));

        gyroLockButton = new JoystickButton(driveStick, Port.GYRO_LOCK);
        solenoid = new JoystickButton(driveStick, Port.SOLENOID);
        ballFire = new JoystickButton(auxStick, Port.BALL_FIRE);
        liftUp = new JoystickButton(auxStick, Port.LIFT_UP);
        liftDown = new JoystickButton(auxStick, Port.LIFT_DOWN);


        DRIVE_RIGHT_X_AXIS = () -> driveStick.getRawAxis(2);
        DRIVE_RIGHT_Y_AXIS = () -> driveStick.getRawAxis(5);
        DRIVE_LEFT_X_AXIS = () -> driveStick.getRawAxis(0);
        DRIVE_LEFT_Y_AXIS = () -> driveStick.getRawAxis(1);

        AUX_RIGHT_X_AXIS = () -> auxStick.getRawAxis(2);
        AUX_RIGHT_Y_AXIS = () -> auxStick.getRawAxis(5);
        AUX_LEFT_X_AXIS = () -> auxStick.getRawAxis(0);
        AUX_LEFT_Y_AXIS = () -> auxStick.getRawAxis(1);
    }
}