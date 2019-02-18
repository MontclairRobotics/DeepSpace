package frc.robot.utils.vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.core.Control;
import org.montclairrobotics.sprocket.control.ButtonAction;

public class DriverCam {

    private enum Cams{
        HATCH_SIDE, CARGO_SIDE
    }

    static UsbCamera cargoCam;
    static UsbCamera hatchCam;
    static VideoSink videoSink;
    static Cams currentCam;

    public static boolean init(){
        hatchCam = CameraServer.getInstance().startAutomaticCapture(0);
        cargoCam = CameraServer.getInstance().startAutomaticCapture(1);
        videoSink = CameraServer.getInstance().getServer();
        videoSink.setSource(hatchCam);
        currentCam = Cams.HATCH_SIDE;

        Control.swapCamera.setPressAction(new ButtonAction() {
            @Override
            public void onAction() {
                switch (currentCam){
                    case CARGO_SIDE:
                        videoSink.setSource(hatchCam);
                        currentCam = Cams.HATCH_SIDE;
                        break;

                    case HATCH_SIDE:
                        videoSink.setSource(cargoCam);
                        currentCam = Cams.CARGO_SIDE;
                        break;
                }
                SmartDashboard.putString("Current Feed", currentCam.name());
            }
        });

        return true;
    }

}
