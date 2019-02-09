package frc.robot.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import org.montclairrobotics.sprocket.utils.Input;

public class UltrasonicSensor implements Input<Double> {
    AnalogInput analogInput;
    Ultrasonic ultrasonic;



    public UltrasonicSensor(){

    }

    @Override
    public Double get() {
        return null;
    }

}
