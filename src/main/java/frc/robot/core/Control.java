package frc.robot.core;

import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.utils.MecanumInput;
import frc.robot.utils.SPOVButton;
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

        // Driver-Assist
        GYRO_LOCK(0,12,0),    // Joystick: Drive, Button: L3
        FIELD_CENTRIC(0,6,0), // Joystick: Drive, Button: L1
        ALIGNMENT(0,4,0),    // Joystick: Drive, Button: L2

        // Lift Controls
        LIFT_RESET(1,1,0),   // Joystick: Aux,  Button: Square
        LIFT_BOT(1,2,0),     // Joystick: Aux,  Button: X
        LIFT_MID(1,3,0),     // Joystick: Aux,  Button: Triangle
        LIFT_TOP(1,4,0),     // Joystick: Aux,  Button Triangle

        // Intake Controls //TODO: Test POV Buttons
        INTAKE_UP(1,1,0),    // Joystick: Aux, Button: DPAD Up
        INTAKE_DOWN(1,4,180),  // Joystick: Aux, Button: DPAD Down

        // Fire Controls
        SOLENOID(1,7,0),     // Joystick: Aux, Button: L2
        BALL_FIRE(1,8,0);    // Joystick: Aux, Button: R2

        private int stick, button, angle;

        Port(int stick, int button, int angle){
            this.stick = stick;
            this.button = button;
            this.angle = angle;
        }

        public int getStick() {
            return stick;
        }

        public int getButton() {
            return button;
        }

        public int getAngle(){
            return angle;
        }
    }

    public static Joystick driveStick;
    public static Joystick auxStick;

    public static MecanumInput dt_input;

    public static Button ballFire;

    public static Button liftReset;
    public static Button liftTop;
    public static Button liftMid;
    public static Button liftBot;

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

        liftReset = new JoystickButton(Port.LIFT_RESET.getStick(), Port.LIFT_RESET.getButton());
        liftTop = new JoystickButton(Port.LIFT_TOP.getStick(), Port.LIFT_TOP.getButton());
        liftMid = new JoystickButton(Port.LIFT_MID.getStick(), Port.LIFT_MID.getButton());
        liftBot = new JoystickButton(Port.LIFT_BOT.getStick(), Port.LIFT_BOT.getButton());

        intakeUp = new SPOVButton(Port.INTAKE_UP.getStick(),
                Port.INTAKE_UP.getButton(),
                Port.INTAKE_UP.getAngle());

        intakeDown = new SPOVButton(Port.INTAKE_DOWN.getStick(),
                Port.INTAKE_DOWN.getButton(),
                Port.INTAKE_DOWN.getAngle());

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