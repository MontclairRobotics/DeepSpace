package frc.robot.utils;

import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.states.State;


public class TeleopState implements State {

    public SprocketRobot sprocketRobot;

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
