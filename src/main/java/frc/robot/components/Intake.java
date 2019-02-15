package frc.robot.components;

import frc.robot.utils.SplitButton;
import org.montclairrobotics.sprocket.control.Button;
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

    public Intake(Input<Double> powerInput, SplitButton rotate, Button launch, Module motorModule, Motor rotateMotor) {
        this.powerInput = powerInput;
        this.launch = launch;
        this.motorModule = motorModule;
        this.rotateMotor = rotateMotor;
        this.rotate = rotate;
        Updater.add(this, Priority.HIGH);

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

            if(rotate.get()){
                setUp();
            }else{
                setDown();
            }
        }else{
            motorModule.set(.5);
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
        // Todo: Wait for electronics for implementation
    }

    public void setDown(){
        // Todo: Wait for electronics for implementation
    }
}
