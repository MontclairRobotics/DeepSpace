package frc.robot.utils.vision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.geometry.Degrees;
import org.montclairrobotics.sprocket.geometry.XY;
import org.montclairrobotics.sprocket.utils.Input;
import org.montclairrobotics.sprocket.utils.PID;
import org.montclairrobotics.sprocket.utils.Togglable;

/**
 * A generic class for correcting angle and distance based on a vision target
 *
 * This class takes in an input and, when enabled, will apply a
 * turn to the drivetrain so that it is in line with vision target
 * at a specified coordinate.
 *
 * This is also a toggleable so it should be attached to a button to allow
 * it to be enabled and disabled
 */
public class VisionCorrection2 implements DTStep, Togglable {

    private boolean enabled;

    private PID correctionX;
    private PID correctionY;

    private Input<Double> visionInX;
    private Input<Double> visionInY;

    /**
     * Create a new Vision correction specifying the target
     */
    public VisionCorrection2(Input<Double> visionInX, Input<Double> visionInY,
                             PID correctionX, PID correctionY,
                             double tgtX, double tgtY){
        this.correctionX = correctionX;
        this.correctionY = correctionY;
        this.correctionX.setInput(visionInX).setTarget(tgtX);
        this.correctionY.setInput(visionInY).setTarget(tgtY);
    }



    @Override
    public DTTarget get(DTTarget dtTarget) {
        if(enabled && SmartDashboard.getBoolean("Hatch Detected",false)){
            return new DTTarget(
                    dtTarget.getDirection().add(new XY(0,correctionY.get())),
                    dtTarget.getTurn().add(new Degrees(correctionX.get()))
            );
        }
        return dtTarget;
    }

    @Override
    public void disable() {
        enabled = false;
    }

    @Override
    public void enable() {
        enabled = true;
    }

}