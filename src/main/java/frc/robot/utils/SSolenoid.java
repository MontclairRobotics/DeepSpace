package frc.robot.utils;

import edu.wpi.first.wpilibj.Solenoid;
import org.montclairrobotics.sprocket.utils.Togglable;

public class SSolenoid implements Togglable {

    Solenoid solenoid;

    public SSolenoid(Solenoid solenoid){
        this.solenoid = solenoid;
    }

    @Override
    public void enable() {
        solenoid.set(true);
    }

    @Override
    public void disable() {
        solenoid.set(false);
    }

    public Solenoid getSolenoid() {
        return solenoid;
    }
}
