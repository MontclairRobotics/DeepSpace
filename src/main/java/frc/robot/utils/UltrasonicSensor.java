package frc.robot.utils;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.SampleRobot;
import org.montclairrobotics.sprocket.utils.Input;

public class UltrasonicSensor implements Input<Double> {
    private AnalogInput sensor;
    private final int channel;

    public UltrasonicSensor(int channel){
        this.channel = channel;
    }
    public void analogValues(){
        int raw = sensor.getValue();
        double volts = sensor.getVoltage();
    }
    public void analogInit(){
        sensor = new AnalogInput(channel);

    }
    @Override
    public Double get() {
        double range = sensor.getRangeInches(); //  fix this
        return range;
    }
}