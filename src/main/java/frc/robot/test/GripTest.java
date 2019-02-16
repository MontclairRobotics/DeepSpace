package frc.robot.test;

import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.utils.NewVisionTarget;
import frc.robot.utils.VisionTarget;
import org.montclairrobotics.sprocket.SprocketRobot;
import org.montclairrobotics.sprocket.utils.Debug;

public class GripTest extends SprocketRobot {
    NewVisionTarget test;
    @Override
    public void robotInit() {
        CameraServer.getInstance().startAutomaticCapture();
        CameraServer.getInstance().addServer(CameraServer.getInstance().getVideo());
    }

    @Override
    public void userTeleopInit() {
        test = new NewVisionTarget(CameraServer.getInstance().getVideo());
    }

    @Override
    public void update() {
        Debug.msg("Vision Target Out", test.get());
    }
}
