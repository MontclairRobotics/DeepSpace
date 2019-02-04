package frc.robot;

import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.JoystickYAxis;
import org.montclairrobotics.sprocket.loop.Priority;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.loop.Updater;
import org.montclairrobotics.sprocket.motors.Module;
import org.montclairrobotics.sprocket.motors.Motor;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.utils.Input;

public class Intake implements Updatable {
    //we love PID

    Module module;
    Button button1;
    Button button2;
    Input<Double> joystick;
    double power = 0.2;
    boolean launching = false;
    public long launchTime = 0;

    public Intake(Module m, Button b1, Button b2, Input<Double> joystick) {
        button1 = b1;
        button2 = b2;
        module = m;
        Updater.add(this, Priority.CONTROL);
        button1.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                launch(1);
            }
        });
    }
    public void setPower(double p){
        power  = p;
    }
   public void launch(double p){
       power = p;
       launching = true;
       launchTime = System.nanoTime();
   }

    @Override
    public void update() {
        power = .05;

        if(launching){
            power = 1;
            if((System.nanoTime() - launchTime) > .5 * Math.pow(10, 9)){
                launching = false;
            }
        }

        if(joystick.get() > .05 || joystick.get() < -.05){ // Joystick is moving
            power = joystick.get();
        }

        module.set(power);
    }
}
