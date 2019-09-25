package frc.robot.utils;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.montclairrobotics.sprocket.utils.Input;

public class HatchInput implements Input<Double> {
    private NetworkTableInstance tableInstance;
    private NetworkTableEntry x;
    private double[] xCoords = new double[2];

    public HatchInput(){
        tableInstance = NetworkTableInstance.getDefault();
    }

    @Override
    public Double get() {
        x = tableInstance.getEntry("hatchX");
        return SmartDashboard.getNumber("hatchX", 200);
    }
}
