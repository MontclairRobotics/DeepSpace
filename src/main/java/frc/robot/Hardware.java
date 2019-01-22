package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.montclairrobotics.sprocket.motors.SEncoder;

public class Hardware {

    public static WPI_TalonSRX fl;
    public static WPI_TalonSRX fr;
    public static WPI_TalonSRX bl;
    public static WPI_TalonSRX br;

    public static void init() {
        fl = new WPI_TalonSRX(1);
        fr = new WPI_TalonSRX(7);
        bl = new WPI_TalonSRX(3);
        br = new WPI_TalonSRX(8);
    }

}
