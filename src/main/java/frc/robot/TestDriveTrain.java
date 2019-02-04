package frc.robot;
import java.util.ArrayList; 
import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.control.ButtonAction;
import org.montclairrobotics.sprocket.drive.DTPipeline;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.DriveModule;
import org.montclairrobotics.sprocket.drive.DriveTrain;
import org.montclairrobotics.sprocket.drive.DriveTrainBuilder;
import org.montclairrobotics.sprocket.drive.DriveTrainType;
import org.montclairrobotics.sprocket.drive.InvalidDriveTrainException;
import org.montclairrobotics.sprocket.drive.steps.Deadzone;
import org.montclairrobotics.sprocket.drive.steps.GyroCorrection;
import org.montclairrobotics.sprocket.drive.utils.GyroLock;
import org.montclairrobotics.sprocket.geometry.XY;
import org.montclairrobotics.sprocket.motors.Motor;
import org.montclairrobotics.sprocket.pipeline.Step;
import org.montclairrobotics.sprocket.utils.Debug;
import org.montclairrobotics.sprocket.utils.PID;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class TestDriveTrain extends SprocketRobot(
    
    DriveTrain dt;
    GyroCorrection correction;
   
    WPI_TalonSRX dt_rightFront;
    WPI_TalonSRX dt_rightBack;
    WPI_TalonSRX dt_leftBack;
    WPI_TalonSRX dt_leftFront;
   
    @Override
    public void robotInit{

        dt_rightFront = new WPI_TalonSRX(1);
        dt_rightBack = new WPI_TalonSRX(2);
        dt_leftBack = new WPI_TalonSRX(3);
        dt_leftFront = new WPI_TalonSRX(4);

        driveStick = new Joystick(Port.driveStick);
        dt_input = new ArcadeDriveInput(DriveStick);
    }

    public void robotInit(){
        public static final int DRIVE_STICK = 0;
        public static final int AUX_STICK = 0;

        public static final int GYRO_LOCK = 1;
        public static final int COMPRESSOR = 3;
        public static final int SOLENOID = 2;
        public static Joystick driveStick;
        public static Joystick auxStick;
    
        public static Button gyroLockButton;
    
        public static ArcadeDriveInput dt_input;
    
        public static Button compressor; 
        public static Button solenoid;
    
        driveStick = new joyStick(Port.DRIVE_STICK);
        auxStick = new joyStick(Port.AUX_STICK);
        gyroLockButton = new joyStick(driveStick, Port.GYRO_LOCK);
        dt_input = new ArcadeDriveInput(DriveStick);
        compressor = new JoystickButton(driveStick, Port.COMPRESSOR);
        solenoid = new JoystickButton(driveStick, Port.SOLENOID);
        Hardware.init();
        Control.init();
        DriveTrainBuilder dtBuilder = new DriveTrainBuilder();
        dtBuilder.addDriveModule(new DriveModule(new XY(-1, 0), new XY(0, 1), new Motor(Hardware.dt_rightFront), new Motor(Hardware.dt_rightBack)));
        dtBuilder.addDriveModule(new DriveModule(new XY(1, 0), new XY(0, -1), new Motor(Hardware.dt_leftFront), new Motor(Hardware.dt_leftBack)));
        dtBuilder.SetInput(Control.dt_input);
        dt.SetDriveTrainType(DriveTrainType.Tank);
    }
    try{
        dt = dtBuilder.build();
    }catch (InvalidDriveTrainException e){
        e.printStackTrace();
    }

}