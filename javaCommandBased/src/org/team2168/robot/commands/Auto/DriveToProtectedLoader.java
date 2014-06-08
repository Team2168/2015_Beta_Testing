package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.CommandBaseRobot;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainTurn_Simple;

/**
 * Drive to protected loading station.
 * Dependent on starting position.
 * 
 * @author James
 */
public class DriveToProtectedLoader extends CommandGroup {
	//These variables are listed in the order their associated commands
	//  will be executed. They are set below depending on initial position.
	private double driveDistance1, //in feet
                   rotateAngle1,   //in degrees
                   driveDistance2, //in feet
                   rotateAngle2,
                   driveDistance3,
                   rotateAngle3;   //in degrees
	
	/**
	 * Drive to the position robot near the protected loading station.
	 */
	public DriveToProtectedLoader() {
		int position = CommandBaseRobot.getInitialPosition();
		
		//Do different things depending on where we started from
		switch(position) {
			case CommandBaseRobot.RIGHT:
//TODO: Test
				driveDistance1 = -4.0; //Drive straight backwards to line (ft)
				rotateAngle1   = -140.0; //Rotate counter clockwise to face loading station(deg.)
				driveDistance2 =   0.0; //done
				rotateAngle2   =   0.0; //done
				driveDistance3 =   0.0;
				rotateAngle3   =   0.0;
				break;
//TODO: Test
			case CommandBaseRobot.CENTER:
				driveDistance1 = -3.5; //Drive backwards (ft)
				rotateAngle1   = 25.0; //Rotate clockwise to face loader wall
				driveDistance2 = 7.0; //Drive forwards across field (ft)
				rotateAngle2   = 45.0; //rotate to face human loader
				driveDistance3 = 0.0;
				rotateAngle3   =   0.0;
				break;
			case CommandBaseRobot.LEFT:
				driveDistance1 =  -3.5; //Drive backwards (ft)
				rotateAngle1   =  35.0; //Rotate to face wall on loader side
				driveDistance2 =  14.0; //Drive forwards across field
				rotateAngle2   =  65.0; //rotate clockwise to face loader(deg.)
				driveDistance3 =   0.0;
				rotateAngle3   =   0.0;
				break;
			default: //just in case
				//TODO: set all to zero
				break;
		}

		if(driveDistance1 != 0.0) {
			addSequential(new DriveDrivetrainStraight(convertDistance(driveDistance1)));
		}
		if(rotateAngle1 != 0.0) {
			addSequential(new DriveDrivetrainTurn_Simple(rotateAngle1));
		}
		if(driveDistance2 != 0.0) {
			addSequential(new DriveDrivetrainStraight(convertDistance(driveDistance2)));
		}
		if(rotateAngle2 != 0.0) {
			addSequential(new DriveDrivetrainTurn_Simple(rotateAngle2));
		}
		if(driveDistance3 != 0.0) {
			addSequential(new DriveDrivetrainStraight(convertDistance(driveDistance3)));
		}
		if(rotateAngle3 != 0.0) {
			addSequential(new DriveDrivetrainTurn_Simple(rotateAngle3));
		}
	}
	
	/**
	 * Adjust value in feet to a number that gets us closer to actual measured
	 * distance. This was derived empirically. 
	 * 
	 * @param in the distance in feet to be converted
	 * @return the converted value in inches
	 */
	private double convertDistance (double in) {
		if (in == 0.0) {
			return 0.0;
		} else {
			return ((in * 12) - 8);
		}
	}
}
