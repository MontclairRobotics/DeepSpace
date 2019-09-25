package frc.robot.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import org.montclairrobotics.sprocket.utils.Input;

public class UltrasonicSensor implements Input<Double> {
    AnalogInput analogInput;
    
    public UltrasonicSensor(int channel){
        analogInput = new AnalogInput(channel);
    }

    @Override
    public Double get() {
        return analogInput.getVoltage() * 5;
    }

}
