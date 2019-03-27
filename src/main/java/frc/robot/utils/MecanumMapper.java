package frc.robot.utils;

import org.montclairrobotics.sprocket.drive.DTMapper;
import org.montclairrobotics.sprocket.drive.DTTarget;
import org.montclairrobotics.sprocket.drive.DriveModule;

/**
 * Created by MHS Robotics on 11/11/2017.
 */

public class MecanumMapper implements DTMapper {

    public void map(DTTarget driveTarget, DriveModule[] driveModules) {
        //Setting up variables
        double x = driveTarget.getDirection().getX();
        double y = driveTarget.getDirection().getY();
        double turn = driveTarget.getTurn().toDegrees();
        double maxPower = 1;

        for(DriveModule module:driveModules)
        {
            double xSign=module.getOffset().getX()>0?1:-1;
            double ySign=module.getOffset().getY()>0?1:-1;
            double dirSign=module.getOffset().crossProduct(module.getForce())>0?1:-1;
            double a = 1.5;
            module.temp=(y*xSign*a*-1+x*ySign*a+turn)*dirSign;
            if(module.temp > maxPower)
            {
                maxPower=module.temp;
            }
        }
        for(DriveModule module:driveModules) {
            module.set(module.temp);
        }
    }
}