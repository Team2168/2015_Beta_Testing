package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to empty the left side of the intake. 
 * @author Shriji
 *
 */
public class DriveLeftTillEmpty extends CommandBase {

	boolean noDisc;
	
	public DriveLeftTillEmpty() {
		requires (intakeSpeed);	
	}
	
	protected void initialize() {
		//Nothing to do here
	}	
	

	/**
	 * if left side is full, empty it and then end the command.
	 * @return 
	 */
	protected void execute() {
		
    	if(intakeSpeed.leftFull()){
    		intakeSpeed.driveIntake(1.0,0.0);
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
