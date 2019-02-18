package frc.robot.components;

import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.loop.Priority;
import org.montclairrobotics.sprocket.loop.Updatable;
import org.montclairrobotics.sprocket.loop.Updater;
import org.montclairrobotics.sprocket.motors.Module;
import org.montclairrobotics.sprocket.utils.Debug;
import org.montclairrobotics.sprocket.utils.DoubleInput;
import org.montclairrobotics.sprocket.utils.PID;

public class Lift implements Updatable {

    public enum  Heights{
        TOP(0), MID(0), BOT(0); // Test for values

        private double pos;
        Heights(double pos){
            this.pos = pos;
        }
    }

    private Module module;
    private Button top, mid, bot, reset;

    private PID correction = new PID(1,0,0)
            .setInput(new DoubleInput(module.getEnc().get()));

    public Lift(Module m, Button top, Button mid, Button bot, Button reset){
        this.module = m;
        correction.setInput(m.getEnc());
        Updater.add(this, Priority.HIGH);
        this.top = top;
        this.mid = mid;
        this.bot = bot;
        this.reset = reset;

        this.top.setHeldAction(new ButtonAction() {
            @Override
            public void onAction() {
                correction.setTarget(Heights.TOP.pos);
            }
        });

        this.mid.setHeldAction(new ButtonAction() {
            @Override
            public void onAction() {
                correction.setTarget(Heights.MID.pos);
            }
        });

        this.bot.setHeldAction(new ButtonAction() {
            @Override
            public void onAction() {
                correction.setTarget(Heights.BOT.pos);
            }
        });

        this.reset.setHeldAction(new ButtonAction() {
            @Override
            public void onAction() {
                correction.setTarget(-100000); // Arbitrary value to drive it to the ground
            }
        });

    }

    @Override
    public void update() {
        module.set(correction.get());
        Debug.msg("Lift Correction", correction);
    }

}
