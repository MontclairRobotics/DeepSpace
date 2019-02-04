package frc.robot;
import org.montclairrobotics.sprocket.control.Button;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.drive.utils.GyroLock;
import org.montclairrobotics.sprocket.geometry.Angle;
import org.montclairrobotics.sprocket.pipeline.Step;

public class Orientation implements Step<DTTarget> {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    GyroCorrection gyro;
    double power = 0.5;

    public Orientation(Button b1, Button b2, Button b3, Button b4, GyroCorrection g){
        button1 = b1;
        button2 = b2;
        button3 = b3;
        button4 = b4;
        gyro = g;
        b1.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                ;
            }
        });
        b2.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
            }
        });
        b3.setPressAction((new ButtonAction() {
            @Override
            public void onAction() {
            }
        }));
        b4.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
            }
        });
    }
    public void zeroDegrees(Button b1){

    }
    public void ninetyDegrees(Button b2){

    }
    public void hundredEightydegrees(Button b3){

    }
    public void twoSeventydegrees(Button b4){

    }

    @Override
    public DTTarget get(DTTarget dtTarget) {
        return null;
    }
}
