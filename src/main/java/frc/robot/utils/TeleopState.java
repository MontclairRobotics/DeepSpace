package frc.robot.utils;

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
        sprocketRobot.teleopInit();
        SprocketRobot.getDriveTrain().useDefaultInput();
    }

    @Override
    public void stop() {
        sprocketRobot.reset();
    }

    @Override
    public void stateUpdate() {
        //sprocketRobot.sprocketUpdate();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
