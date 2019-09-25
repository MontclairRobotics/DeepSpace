package frc.robot.utils;


import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.geometry.Angle;
import org.montclairrobotics.sprocket.geometry.Polar;
import org.montclairrobotics.sprocket.utils.PID;
import org.montclairrobotics.sprocket.utils.Togglable;

public class UltrasonicCorrection implements DTStep, Togglable {


    private UltrasonicSensor sensor;
    private PID correction;
    private boolean enabled;

    public UltrasonicCorrection(UltrasonicSensor sensor, double target, PID correction){
        this.correction = correction;
        this.sensor = sensor;
        correction.setTarget(target);
        correction.setInput(sensor);
    }

    @Override
    public DTTarget get(DTTarget dtTarget) {
        if (enabled) {
            return new DTTarget(new Polar(correction.get(), Angle.ZERO), dtTarget.getTurn());
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
