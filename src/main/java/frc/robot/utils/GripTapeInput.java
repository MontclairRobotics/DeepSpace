package frc.robot.utils;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import org.montclairrobotics.sprocket.utils.Input;

public class GripTapeInput implements Input<Double> {
    private NetworkTableInstance tableInstance;
    private NetworkTableEntry x;
    private double[] xCoord = new double[1];

    public GripTapeInput(){
        tableInstance = NetworkTableInstance.getDefault();
    }

    @Override
    public Double get() {
        x = tableInstance.getTable("GRIP/Tape").getEntry("centerX");
        xCoord = x.getDoubleArray(new double[]{120});
        try {
            return xCoord[0];
        }catch (ArrayIndexOutOfBoundsException e){
            return 120D;
        }
    }
}
