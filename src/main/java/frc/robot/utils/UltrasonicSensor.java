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
    public void analogInit(){
        sensor = new AnalogInput(channel);
    }
    @Override
    public Double get() {
        double volts = sensor.getVoltage(); //gets volts
        double mm = volts * 5 / 4.88 * 1000; //converts volts into millimeters
        double inches = mm * 0.0394; //converts millimeters to inches
        return inches;
    }
}