package frc.robot.utils;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class SP implements SpeedController {

    VictorSP controller;

    public SP(VictorSP controller){
        this.controller = controller;
    }

    @Override
    public void set(double v) {
        controller.set(v);
    }

    @Override
    public double get() {
        return controller.get();
    }

    @Override
    public void setInverted(boolean b) {
        controller.setInverted(b);
    }

    @Override
    public boolean getInverted() {
        return controller.getInverted();
    }

    @Override
    public void disable() {
        controller.disable();
    }

    @Override
    public void stopMotor() {
        controller.stopMotor();
    }

    @Override
    public void pidWrite(double v) {
        controller.pidWrite(v);
    }
}
