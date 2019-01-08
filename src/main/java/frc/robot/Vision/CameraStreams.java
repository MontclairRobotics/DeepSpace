package frc.robot.Vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CameraStreams{

    public enum Feed{
        FRONT("Front"), LINE("Line");

        private final String name;

        private Feed(String name){
            this.name = name;
        }
    }

    private final int frontSource;
    private final int lineSource;

    private VideoSource frontCamera;
    private UsbCamera lineCamera;
    private VideoSink videoServer;

    public CameraStreams(int frontSource, int lineSource){
        this.frontSource = frontSource;
        this.lineSource = lineSource;
    }

    public boolean setup(){
        this.frontCamera = CameraServer.getInstance().startAutomaticCapture(this.frontSource);
        this.lineCamera = CameraServer.getInstance().startAutomaticCapture(this.lineSource);
        this.videoServer  = CameraServer.getInstance().getServer();
        
        return true;
    }

    public void run(Feed feed){
        SmartDashboard.putString("Camera Feed",feed.name);
        switch(feed){
            case FRONT:
                videoServer.setSource(this.frontCamera);
            break;

            case LINE:
                videoServer.setSource(this.lineCamera);
            break;

        }
    }
   
}