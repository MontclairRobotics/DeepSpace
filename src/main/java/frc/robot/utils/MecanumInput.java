package frc.robot.utils;

import edu.wpi.first.wpilibj.Joystick;
import org.montclairrobotics.sprocket.drive.DTInput;
import org.montclairrobotics.sprocket.geometry.*;
import org.montclairrobotics.sprocket.utils.Input;

public class MecanumInput implements DTInput {
    public Input<Vector> translate;
    public Input<Angle> rotate;

    public MecanumInput(Joystick translateStick, Input<Double> rotateInput){
        translate = new Input<Vector>() {
            @Override
            public Vector get() {
                return new XY(-translateStick.getRawAxis(0), translateStick.getRawAxis(5));
            }
        };

        rotate = new Input<Angle>() {
            @Override
            public Angle get() {
                return new Radians(rotateInput.get() * .2);
            }
        };
    }

    @Override
    public Vector getDir() {
        return translate.get();
    }

    @Override
    public Angle getTurn() {
        return rotate.get();
    }
}
