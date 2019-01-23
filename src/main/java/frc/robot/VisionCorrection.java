package frc.robot;

import org.montclairrobotics.sprocket.drive.DTStep;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.geometry.Degrees;
import org.montclairrobotics.sprocket.utils.Debug;
import org.montclairrobotics.sprocket.utils.Input;
import org.montclairrobotics.sprocket.utils.PID;
import org.montclairrobotics.sprocket.utils.Togglable;

/**
 * A generic class for correcting angle based on a vision target
 * 
 * This class takes in an input and, when enabled, will apply a 
 * turn to the drivetrain so that it is in line with vision target
 * at a specified coordinate.
 * 
 * This is also a toggleable so it should be attached to a button to allow
 * it to be enabled and dissabled 
 */
public class VisionCorrection implements DTStep, Togglable {
    /** Whether or not the correction is active */
    private boolean enabled;

    /** The PID controller used to correct to the specified target */
    private PID correction; 
    /** An input (usually from the network tables) */
    private Input<Double> visionIn;

    private double target; 

    /** 
     * Create a new Vision correction specifying the target
     */
    public VisionCorrection(Input<Double> visionIn, PID correction, double target){
        this(visionIn, correction);
        setTarget(target);
    }

    public VisionCorrection(Input<Double> visionIn, PID correction){
        this.correction = correction;
        this.visionIn = visionIn;
        correction.setInput(visionIn);
    }

    /**
     * Set the target coordinate for the vision target
     * 
     * This method is in place so that the target can be changed during runtime 
     * to allow for easier tuing and debugging 
     * 
     * @param target the specified target that the robot will aim to correct to 
     */
    public void setTarget(double target){
        this.target = target;
        correction.setTarget(target);
    }



    @Override
    public DTTarget get(DTTarget dtTarget) {
        if(enabled){
            return new DTTarget(
                dtTarget.getDirection(), 
                dtTarget.getTurn().add(new Degrees(correction.get()))
            );
        }
        debug();
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

    /**
     * Debugs out all relevant information about the correction. 
     */
    public void debug(){
        Debug.msg("Current Vision Value", visionIn.get());
        Debug.msg("Target Vision Value", target);
        Debug.msg("Vision Turn Correction", enabled ? correction.get() : 0);
        Debug.msg("Vision Correction Enabled", enabled);
    }
}