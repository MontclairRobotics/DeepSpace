package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.POVButton;
import org.montclairrobotics.sprocket.control.Button;

public class SPOVButton extends Button {

    private Joystick joystick;
    private int angle;
    private int id;

    public SPOVButton(Joystick joystick, int angle, int povID){
        this.joystick = joystick;
        this.angle = angle;
        this.id = povID;
    }

    public SPOVButton(int joystick, int angle, int povID){
        this(new Joystick(joystick), angle, povID);
    }

    @Override
    public Boolean get() {
        return joystick.getPOV(id) == angle;
    }
}
