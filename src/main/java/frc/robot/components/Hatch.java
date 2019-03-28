package frc.robot.components;

import edu.wpi.first.wpilibj.Solenoid;
import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.states.MultiState;
import org.montclairrobotics.sprocket.states.StateMachine;

public class Hatch implements Updatable {
    private Solenoid extension1;
    private Solenoid extension2;
    private Solenoid fire;


    private Button fireButton;
    private Button intakeButton;

    StateMachine fireSequence;

    public Hatch(Button fireButton, Button intakeButton, Solenoid extension1, Solenoid extension2, Solenoid fire){
        this.fireButton = fireButton;
        this.intakeButton = intakeButton;
        this.extension1 = extension1;
        this.extension2 = extension2;
        this.fire = fire;
        fireSequence = new StateMachine(
                // Retract first Part
                new SolenoidState(extension1, false, .25),
                // Retract fire
                new SolenoidState(fire, true, .25),
                // Retract second stage and bring in fire pistons
                new MultiState(
                        new SolenoidState(extension2, false, .25),
                        new SolenoidState(fire, false, 0)
                )
        );

        fireButton.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                fire();
            }
        });
        intakeButton.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                intake();
            }
        });
    }

    @Override
    public void update() {
        fireSequence.stateUpdate();
    }

    private void fire(){
        fireSequence.start();
    }

    private void intake(){
        extension1.set(true);
        extension2.set(true);
        fire.set(true);
    }
}
