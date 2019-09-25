package frc.robot.components;

import frc.robot.utils.BangBang;
import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.loop.Priority;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.loop.Updater;
import org.montclairrobotics.sprocket.motors.Module;
import org.montclairrobotics.sprocket.utils.Debug;
import org.montclairrobotics.sprocket.utils.Input;
import org.montclairrobotics.sprocket.utils.PID;

public class Lift implements Updatable {
    private int[] positions = {
            0,
            -650110/3,
            -650110/2,
            -2 * 650110 /3,
            -650110
    }; // Todo: test for values
    private Input<Double> override;
    private Button up;
    private Button down;
    private int pos = 0;
    public boolean manual;
    private PID correction = new PID(.00001, 0, 0);
    private Module module;

    public Lift(Input<Double> override, Button up, Button down, Module m){
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
        this.module = m;
        correction.setInput(m.getEnc());
        Updater.add(this, Priority.HIGH);
    }

    public void increment(){
        manual = false;
        if(manual){
//            calcClosestState();
            manual = false;
        }
        if(pos + 1 < positions.length){
            pos++;
        }
        correction.setTarget(positions[pos]);
    }

    public void decrement(){
        manual = false;
        if(manual){
//            calcClosestState();
            manual = false;
        }
        if(pos - 1 > 0){
            pos--;
        }
        correction.setTarget(positions[pos]);
    }


    @Override
    public void update() {
        correction.setTarget(positions[pos]);
        if(!manual){
            if(Math.abs(override.get()) > 0.01){
                manual = true;
            }else{
                module.set(correction.get());
            }
        }else{
            module.set(override.get());
            if(Math.abs(override.get()) < 0.05){
                module.set(-.05);
            }
        }
        Debug.msg("Lift Pos", pos);
        Debug.msg("Lift tics", module.getEnc().getTicks());
        Debug.msg("Lift Correction", correction.get());
        Debug.msg("Lift Target", correction.getTarget());
    }
    private void calcClosestState(){
        int min = 0;
        for(int i = 0; i < positions.length; i++){
            if(Math.abs(positions[i] - module.getEnc().getTicks()) < Math.abs(positions[min] - module.getEnc().getTicks())){
                min = i;
            }
        }
        pos = min;
    }
}
