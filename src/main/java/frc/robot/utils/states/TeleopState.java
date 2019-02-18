package frc.robot.utils.states;

import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.states.State;


public class TeleopState implements State {

    private SprocketRobot sprocketRobot;

    public TeleopState(SprocketRobot robot){
        this.sprocketRobot = robot;
    }

    @Override
    public void start() {
        sprocketRobot.start();
    }

    @Override
    public void stop() {
        sprocketRobot.reset();
    }

    @Override
    public void stateUpdate() {
        sprocketRobot.sprocketUpdate();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
