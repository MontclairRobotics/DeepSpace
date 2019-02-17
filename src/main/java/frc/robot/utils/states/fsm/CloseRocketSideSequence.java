package frc.robot.utils.states.fsm;

import org.montclairrobotics.sprocket.auto.states.DriveEncoderGyro;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.geometry.Angle;
import org.montclairrobotics.sprocket.states.State;
import org.montclairrobotics.sprocket.states.StateMachine;

public class CloseRocketSideSequence extends StateMachine {

    public CloseRocketSideSequence(GyroCorrection correction){
        super(
                new DriveEncoderGyro(10, 0.8, Angle.ZERO, true, correction),
                new State() {
                    @Override
                    public void start() {

                    }

                    @Override
                    public void stop() {

                    }

                    @Override
                    public void stateUpdate() {

                    }

                    @Override
                    public boolean isDone() {
                        return false;
                    }
                }
        );
    }

}
