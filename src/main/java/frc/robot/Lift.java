package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.motors.Module;

public class Lift implements Updatable {
    private Module module;
    private int pos;
    private double power = 0.5;
    private boolean isManual = false;
    private Joystick joystick;
    private int[] targets = {1, 2, 3, 4, 5, 6, 7, 8};


    Joystick j;
    public Lift(Module m) {
        module = m;
    }
    public Lift(Module m, Joystick j) {
        module = m;
        joystick = j;
    }

    public void update() {
        if(isManual) {
            module.set(joystick.getY());
        }
        else {


            if(module.getDistance().get() > targets[pos]) {
                increment();
                module.set(-power);
            }
            else if(module.getDistance().get() > targets[pos]) {
                decrement();
                module.set(power);
            }
            else {
                module.set(0);
            }
        }
    }

    public void increment() {
        pos++;
    }
    public void decrement() {
        pos--;
    }

}
