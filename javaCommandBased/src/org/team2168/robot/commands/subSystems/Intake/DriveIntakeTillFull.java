package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to actuate the intake to stow position.
 * 
 * @author Shriji
 *
 */
public class DriveIntakeTillFull extends CommandBase {

	boolean disc;
	
	public DriveIntakeTillFull() {
		requires (intakeSpeed);	
	}
	
	protected void initialize() {
		//Nothing to do here
	}	
	

	/**
	 * if one disc is present, stop motors and end command. 
	 * if discs aren't present run motors till present.
	 * @return 
	 */
	protected void execute() {
		
//		System.out.println("discs present = " + intakeSpeed.getNumberOfDiscs());
		
    	if(intakeSpeed.leftFull() & intakeSpeed.rightFull()){
    		intakeSpeed.driveIntakeTillFull(0.0,0.0);
			disc = true;
		} else{												
		    intakeSpeed.driveIntakeTillFull(.8,.8);			
			disc = false;
		}		
	}

	protected boolean isFinished() {
		return disc;
	}
	
	protected void interrupted() {
		end();
	}

	
	protected void end() {
		//Stop Intake Motors
		intakeSpeed.driveIntake(0.0, 0.0);
	}

}
