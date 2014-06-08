package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.CommandBaseRobot;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainTurn_Simple;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class DriveToFieldCenter extends CommandGroup {
	//These variables are listed in the order their associated commands
	//  will be executed. They are set below depending on initial position.
	private double driveDistance1, //in feet
                   rotateAngle1,   //in degrees
                   driveDistance2, //in feet
                   rotateAngle2;   //in degrees
	
	/**
	 * Drive to the center of the field.
	 */
	public DriveToFieldCenter() {
		int position = CommandBaseRobot.getInitialPosition();
		
		//Do different things depending on where we started from
		switch(position) {
//TODO: TEST
			case CommandBaseRobot.RIGHT:
				driveDistance1 =  -2.0; //Drive backwards (ft)
				rotateAngle1   =  30.0; //Rotate clockwise (deg.)
				driveDistance2 =  -6.5; //Drive backwards (ft)
				rotateAngle2   =   0.0; //rotate clockwise (deg.)
				break;
			case CommandBaseRobot.CENTER:
				driveDistance1 =  -7.0; //Drive backwards (ft)
				rotateAngle1   =   0.0; //Rotate clockwise (deg.)
				driveDistance2 =   0.0; //Drive backwards (ft)
				rotateAngle2   =   0.0; //rotate clockwise (deg.)
				break;
			case CommandBaseRobot.LEFT:
				driveDistance1 =  -2.0; //Drive backwards (ft)
				rotateAngle1   = -36.0; //Rotate counter-clockwise (deg.)
				driveDistance2 =  -8.0; //Drive backwards (ft)
				rotateAngle2   =   0.0; //rotate counter-clockwise (deg.)
				break;
			default: //just in case
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
		
	}
	
	/**
	 * This a value in feet to a number 
	 * This was derived empirically. 
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
