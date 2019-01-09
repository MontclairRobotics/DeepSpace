package frc.robot.Autonomous;

import org.montclairrobotics.cyborg.CBAutonomous;
import org.montclairrobotics.cyborg.Cyborg;
import org.montclairrobotics.cyborg.data.CBStdDriveRequestData;
import org.montclairrobotics.cyborg.utils.CBStateMachine;

import frc.robot.Data.ControlData;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class TestAuto extends CBAutonomous {
	
	CBStdDriveRequestData stdDriveRequestData;

	double autoDriveSpeed = 0.5;

	// 3 Waypoints
	Waypoint[] points = new Waypoint[] {
    	new Waypoint(-4, -1, Pathfinder.d2r(-45)), // Waypoint @ x=-4, y=-1, exit angle=-45 degrees
    	new Waypoint(-2, -2, 0),                   // Waypoint @ x=-2, y=-2, exit angle=0 radians
    	new Waypoint(0, 0, 0)                      // Waypoint @ x=0, y=0,   exit angle=0 radians
	};

	// Create the Trajectory Configuration
	//
	// Arguments:
	// Fit Method:          HERMITE_CUBIC or HERMITE_QUINTIC
	// Sample Count:        SAMPLES_HIGH (100 000)
	//                      SAMPLES_LOW  (10 000)
	//                      SAMPLES_FAST (1 000)
	// Time Step:           0.05 Seconds
	// Max Velocity:        1.7 m/s
	// Max Acceleration:    2.0 m/s/s
	// Max Jerk:            60.0 m/s/s/s
	Trajectory.Config config = new Trajectory.Config(
		Trajectory.FitMethod.HERMITE_CUBIC, 
		Trajectory.Config.SAMPLES_HIGH, 
		0.05, 1.7, 2.0, 60.0
	);

	// Generate the trajectory
	Trajectory trajectory = Pathfinder.generate(points, config);
	
	// Wheelbase Width = 0.5m
	TankModifier modifier = new TankModifier(trajectory).modify(0.5);
	
	EncoderFollower left  = new EncoderFollower(modifier.getLeftTrajectory());
	EncoderFollower right = new EncoderFollower(modifier.getRightTrajectory());
	
	private enum AutoStates {start};

	private class AutoStateMachine extends CBStateMachine<AutoStates> {


		protected AutoStateMachine(AutoStates start) {
			super(start);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void calcNextState() {
			
		}

		@Override
		protected void doTransition() {
			stdDriveRequestData.direction.setXY(0, modifier.getLeftTrajectory());
		}

		@Override
		protected void doCurrentState() {
		}
		
	}
	
	
	
	@Override
	public void init() {
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}