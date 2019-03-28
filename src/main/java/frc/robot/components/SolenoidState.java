package frc.robot.components;

import edu.wpi.first.wpilibj.Solenoid;
import org.montclairrobotics.sprocket.states.State;

public class SolenoidState implements State {
    private long startTimeMillis;
    private Solenoid solenoid;
    private boolean actuate;
    private double time;

    public SolenoidState(Solenoid solenoid, boolean actuate, double time) {
        this.solenoid = solenoid;
        this.actuate = actuate;
        this.time = time;
    }

    @Override
    public void start() {
        startTimeMillis = System.currentTimeMillis();
    }

    @Override
    public void stop() {

    }

    @Override
    public void stateUpdate() {
        solenoid.set(actuate);
    }

    @Override
    public boolean isDone() {
        return (System.currentTimeMillis() - startTimeMillis) * 1000 > time;
    }
}
