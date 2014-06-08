package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to empty the right side on the intake.
 * 
 * @author Shriji
 *
 */
public class DriveRightTillEmpty extends CommandBase {

	boolean noDisc;
	
	public DriveRightTillEmpty() {
		requires (intakeSpeed);	
	}
	
	protected void initialize() {
		//Nothing to do here
	}	
	

	/**
	 * if right side is full, empty it and then end the command.
	 * @return 
	 */
	protected void execute() {
		
    	if(intakeSpeed.rightFull()){
    		intakeSpeed.driveIntake(0.0,1.0);
			noDisc = false;
		} else{											
			noDisc = true;
		}		
	}

	protected boolean isFinished() {
		return noDisc;
	}
	
	protected void interrupted() {
		end();
	}

	
	protected void end() {
		//Stop Intake Motors
		intakeSpeed.driveIntake(0.0, 0.0);
	}

}
