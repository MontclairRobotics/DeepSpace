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

    public enum Port{

        GYRO_LOCK(0,12), // Joystick: 0, Button: 12
        FIELD_CENTRIC(0,6), // Joystick: 0, Button: 6
        SOLENOID(0,7), // Joystick: 0, Button: 7
        BALL_FIRE(1,7), // Joystick: 1, Button: 7
        INTAKE_UP(1,1), // Joystick: 1, Button: 1
        INTAKE_DOWN(1,3), // Joystick: 1, Button: 3
        AUTO_HATCH(0,4), // Joystick: 0, Button: 4
        LIFT_DOWN(1,1), // Joystick: 1, Button: 1
        LIFT_UP(1,4); // Joystick: 1, Button: 4

        private int stick, button;

        Port(int stick, int button){
            this.stick = stick;
            this.button = button;
        }

        public int getStick() {
            return stick;
        }

        public int getButton() {
            return button;
        }
    }

    public static Joystick driveStick;
    public static Joystick auxStick;

    public static MecanumInput dt_input;

    public static Button ballFire;
    public static Button liftUp;
    public static Button liftDown;
    public static Button intakeUp;
    public static Button intakeDown;


    public static Input<Double> DRIVE_RIGHT_X_AXIS;
    public static Input<Double> DRIVE_RIGHT_Y_AXIS;
    public static Input<Double> DRIVE_LEFT_X_AXIS;
    public static Input<Double> DRIVE_LEFT_Y_AXIS;

    public static Input<Double> AUX_RIGHT_X_AXIS;
    public static Input<Double> AUX_RIGHT_Y_AXIS;
    public static Input<Double> AUX_LEFT_X_AXIS;
    public static Input<Double> AUX_LEFT_Y_AXIS;

    public static void init(){
        driveStick = new Joystick(0);
        auxStick = new Joystick(1);

        dt_input = new MecanumInput(driveStick, () -> -driveStick.getRawAxis(2));

        ballFire = new JoystickButton(Port.BALL_FIRE.getStick(), Port.BALL_FIRE.getButton());
        liftUp = new JoystickButton(Port.LIFT_UP.getStick(), Port.LIFT_UP.getButton());
        liftDown = new JoystickButton(Port.LIFT_DOWN.getStick(), Port.LIFT_DOWN.getButton());

        intakeUp = new JoystickButton(Port.INTAKE_UP.getStick(), Port.INTAKE_UP.getButton());
        intakeDown = new JoystickButton(Port.INTAKE_DOWN.getStick(), Port.INTAKE_DOWN.getButton());

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