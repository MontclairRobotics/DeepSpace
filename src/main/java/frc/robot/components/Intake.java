package frc.robot.components;

import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.motors.Module;
import org.montclairrobotics.sprocket.utils.Input;

public class Intake implements Updatable {

    private Input<Double> powerInput;
    private Button launch;
    private Module motorModule;

    public Intake(Input<Double> powerInput, Button launch, Module motorModule) {
        this.powerInput = powerInput;
        this.launch = launch;
        this.motorModule = motorModule;
    }

    public double getPower(){
        return powerInput.get();
    }

    @Override
    public void update() {
        double motorPower = .05;
        if(powerInput.get() > .05) {
            motorPower = powerInput.get();
        }
        if(launch.get()){
            motorPower = 1;
        }
        motorModule.set(motorPower);
    }
}
