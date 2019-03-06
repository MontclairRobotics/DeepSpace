package frc.robot.utils;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.montclairrobotics.sprocket.utils.Input;

public class GripTapeInput implements Input<Double> {
    private NetworkTableInstance tableInstance;
    private NetworkTableEntry x;
    private double[] xCoords = new double[2];

    public GripTapeInput(){
        tableInstance = NetworkTableInstance.getDefault();
    }

    @Override
    public Double get() {
        x = tableInstance.getEntry("x");
        xCoords = x.getDoubleArray(new double[]{70, 70});
        return (xCoords[0] + xCoords[1])/2;
    }
}
