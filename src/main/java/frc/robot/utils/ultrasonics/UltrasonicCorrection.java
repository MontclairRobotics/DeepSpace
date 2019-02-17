package frc.robot.utils.ultrasonics;


import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.geometry.Angle;
import org.montclairrobotics.sprocket.geometry.Polar;
import org.montclairrobotics.sprocket.utils.PID;
import org.montclairrobotics.sprocket.utils.Togglable;

public class UltrasonicCorrection implements DTStep, Togglable {


    private UltrasonicSensor sensor;
    private PID correction;
    private Angle direction;
    private boolean enabled;

    public UltrasonicCorrection(UltrasonicSensor sensor, Angle angle, PID correction){
        this.correction = correction;
        this.sensor = sensor;
        this.direction = angle;
    }

    @Override
    public DTTarget get(DTTarget dtTarget) {
        if (enabled) {
            return new DTTarget(new Polar(correction.get(), direction), dtTarget.getTurn());
        }else{
            return dtTarget;
        }
    }

    @Override
    public void enable() {
        enabled = true;
    }

    @Override
    public void disable() {
        enabled = false;
    }
}
