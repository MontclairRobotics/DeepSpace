package frc.robot.utils;

import edu.wpi.first.wpilibj.Encoder;
import org.montclairrobotics.sprocket.loop.Priority;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.loop.Updater;
import org.montclairrobotics.sprocket.motors.SEncoder;
import org.montclairrobotics.sprocket.utils.Debug;
import org.montclairrobotics.sprocket.utils.Input;

public class LiftLimit implements Input<Boolean>, Updatable {
    LimitSwitch limitSwitch;
    int encoderStop;
    SEncoder encoder;
    String name;

    public LiftLimit(LimitSwitch limitSwitch, SEncoder encoder, int encoderStop, String name){
        this.limitSwitch = limitSwitch;
        this.encoder = encoder;
        this.encoderStop = encoderStop;
        this.name = name;
        Updater.add(this, Priority.LOW);
    }

    @Override
    public Boolean get() {
        return Math.abs(encoder.get()) > encoderStop || limitSwitch.get();
    }

    @Override
    public void update() {
        Debug.msg(name + "Lift Limit", get());
        Debug.msg(name + "Lift Encoder", encoder.get());
        Debug.msg(name + "Lift Switch", limitSwitch.get());
        if(limitSwitch.get()){
            encoder.reset();
        }
    }
}
