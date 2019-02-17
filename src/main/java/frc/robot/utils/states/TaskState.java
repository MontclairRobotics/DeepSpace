package frc.robot.utils.states;

import frc.robot.components.Lift;
import org.montclairrobotics.sprocket.auto.AutoState;
import org.montclairrobotics.sprocket.control.Button;

//TODO
public class TaskState extends AutoState {

    public enum GamePiece{
        HATCH, CARGO
    }

    public enum Task{
        INTAKE, OUTTAKE
    }

    public enum TargetLevel{
        BOT, MID, TOP
    }

    private boolean cancel = false;

    private GamePiece gamePiece;
    private Task task;
    private TargetLevel targetLevel;

    private Lift lift;
    private Button cancelButton;
    private Button fireButton;

    public TaskState(GamePiece gamePiece, Task task, TargetLevel targetLevel,
                     Lift lift, Button cancelButton, Button fireButton){
            this.gamePiece = gamePiece;
            this.task = task;
            this.targetLevel = targetLevel;
            this.lift = lift;
            this.fireButton = fireButton;
    }

    @Override
    public void userStart() {
        switch (task){
            case INTAKE:
                //set lift bot pos
                break;

            case OUTTAKE:
                switch (targetLevel){
                    case TOP:
                        //set lift mid pos
                        break;

                    default:
                        //set lift bot pos
                }
        }
    }

    @Override
    public void userStop() {
        if (!cancel){
            if(task == Task.OUTTAKE){
                switch (gamePiece){
                    case CARGO:
                        // Fire Cargo Intake System
                        break;

                    case HATCH:
                        // Fire pneumatics
                        break;
                }
            }
        }
    }

    @Override
    public void stateUpdate() {
        switch (task){
            case INTAKE:
                // Drive based on Hatch Position on alliance wall
                break;

            case OUTTAKE:
                switch (targetLevel){
                    case BOT:
                        // Drive based on Retro reflective tape
                        break;

                    default:
                        // Drive based on Hatch Position
                }
                break;
        }

    }

    @Override
    public boolean isDone() {
        if(cancelButton.get() || fireButton.get()){
            cancel = cancelButton.get();
            return true;
        }else{
            cancel = cancelButton.get();
            return false;
        }
    }
}
