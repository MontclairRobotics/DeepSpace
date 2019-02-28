package frc.robot.components;

import frc.robot.utils.SplitButton;
import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.loop.Priority;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.loop.Updater;
import org.montclairrobotics.sprocket.motors.Module;
import org.montclairrobotics.sprocket.motors.Motor;
import org.montclairrobotics.sprocket.utils.Input;

public class Intake implements Updatable {

    private Input<Double> powerInput;
    private Button launch;
    private Module motorModule;
    private Motor rotateMotor;
    private boolean auto;
    private SplitButton rotate;

    public Intake(Input<Double> powerInput, Button rotateUp, Button rotateDown, Button launch, Module motorModule, Motor rotateMotor) {
        this.powerInput = powerInput;
        this.launch = launch;
        this.motorModule = motorModule;
        this.rotateMotor = rotateMotor;
        Updater.add(this, Priority.HIGH);
        rotateUp.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                setUp();
            }
        });
        rotateUp.setReleaseAction(new ButtonAction() {
            @Override
            public void onAction() {
                rotateMotor.set(0);
            }
        });
        rotateDown.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                setDown();
            }
        });
        rotateDown.setReleaseAction(new ButtonAction() {
            @Override
            public void onAction() {
                rotateMotor.set(0);
            }
        });
    }

    public double getPower(){
        return powerInput.get();
    }

    @Override
    public void update() {
        double motorPower = .05;
        if(!auto) {
            if (Math.abs(powerInput.get()) > .05) {
                motorPower = powerInput.get();
            }
            if (launch.get()) {
                motorPower = 1;
            }
            motorModule.set(motorPower);
        }else{
            motorModule.set(.1);
            setDown();
        }
    }

    public void enableAuto(){
        auto = true;
    }

    public void disableAuto(){
        auto = false;
    }

    public void setUp(){
        rotateMotor.set(1);
    }

    public void setDown(){
        rotateMotor.set(-1);
    }
}
