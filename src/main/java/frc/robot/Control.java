package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.montclairrobotics.sprocket.control.ArcadeDriveInput;

public class Control {
    private static Joystick driveStick;
    private static Joystick controlStick;
    public static ArcadeDriveInput dtInput;

    public static void init() {
        driveStick = new Joystick(0);
        dtInput = new ArcadeDriveInput(driveStick);
    }
}
