package frc.robot.utils;

import edu.wpi.first.wpilibj.Compressor;
import org.montclairrobotics.sprocket.loop.Priority;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.loop.Updater;
import org.montclairrobotics.sprocket.utils.Togglable;

public class PressureRegulator implements Togglable, Updatable {
    Compressor c;
    private boolean enabled;
    public PressureRegulator(Compressor c){
        this.c = c;
        Updater.add(this, Priority.CONTROL);
    }

    @Override
    public void update() {
        if(!c.getPressureSwitchValue() && enabled){
            c.setClosedLoopControl(true);
        }else{
            c.setClosedLoopControl(false);
        }
    }

    @Override
    public void enable() {
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }
}