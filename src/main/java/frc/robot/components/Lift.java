package frc.robot.components;

import frc.robot.utils.BangBang;
import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.control.JoystickYAxis;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.motors.Module;

public class Lift implements Updatable {
    private int[] positions = {}; // Todo: test for values
    private JoystickYAxis override;
    private Button up;
    private Button down;
    private int pos;
    public boolean manual;
    private BangBang correction = new BangBang(50, 1);
    private Module module;

    public Lift(JoystickYAxis override, Button up, Button down, Module m){
        this.override = override;
        this.up = up;
        this.down = down;
        this.up.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                increment();
            }
        });
        this.down.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                decrement();
            }
        });
        this.module = module;
        correction.setInput(m.getEnc());
    }

    public void increment(){
        if(manual){
            calcClosestState();
            manual = false;
        }
        if(pos + 1 < positions.length){
            pos++;
        }
    }

    public void decrement(){
        if(manual){
            calcClosestState();
            manual = false;
        }
        if(pos - 1 > 0){
            pos--;
        }
    }


    @Override
    public void update() {
        if(!manual){
            if(override.get() > 0.01){
                manual = false;
            }else{
                module.set(correction.get());
            }
        }else{
            module.set(override.get());
        }
    }
    private void calcClosestState(){
        int min = 0;
        for(int i = 1; i < positions.length; i++){
            if(Math.abs(positions[i] - module.getDistance().get()) < positions[min]){
                min = i;
            }
        }
        pos = min;
    }
}
