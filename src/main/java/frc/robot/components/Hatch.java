package frc.robot.components;

import edu.wpi.first.wpilibj.Solenoid;
import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.loop.Priority;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.states.MultiState;
import org.montclairrobotics.sprocket.states.StateMachine;
import org.montclairrobotics.sprocket.loop.Updater;

public class Hatch implements Updatable {
    private Solenoid extension1;
    // private Solenoid extension2;
    private Solenoid fire;


    private Button fireButton;
    private Button intakeButton;
    private long fireStart;
    private boolean fireing;

    StateMachine fireSequence;

    public Hatch(Button fireButton, Button intakeButton, Solenoid extension1, Solenoid extension2, Solenoid fire){
        this.fireButton = fireButton;
        this.intakeButton = intakeButton;
        this.extension1 = extension1;
        
        //this.extension2 = extension2;
        this.fire = fire;
        // fireSequence = new StateMachine(false, 
        //         // Retract first Part
        //         new SolenoidState(extension1, false, .25),
        //         // Retract fire
        //         new SolenoidState(fire, true, .25),
        //         // Retract second stage and bring in fire pistons
        //         new MultiState(
        //                 new SolenoidState(extension1, false, .25),
        //                 new SolenoidState(fire, false, 0)
        //         )
        // );

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
        Updater.add(this, Priority.LOW);
    }

    @Override
    public void update() {
        if(fireing && System.currentTimeMillis() - fireStart > 500){
            fire.set(true);
            fireing = false;
        }
    }

    private void fire(){
        extension1.set(false);
        fireStart = System.currentTimeMillis();
        fireing = true;
    }

    private void intake(){
        extension1.set(true);
    }
}
