package frc2168_2013.commands.subSystems.Intake;

import frc2168_2013.commands.CommandBase;

/**
 * A command to actuate the intake to hopper position.
 * 
 * @author Shriji
 *
 */
public class IntakeHopperPosition extends CommandBase {

	public IntakeHopperPosition() {
		requires (intakePos);
	}

	
	protected void initialize() {
		//Nothing to do here
	}

	/**
	 * Sets the intake to the hopper position
	 */
	protected void execute() {
		intakePos.hopper();
	}

	
	protected void interrupted() {
		//Nothing to do here
	}

	
	protected void end() {
		//Nothing to do here
	}

	
	protected boolean isFinished() {
		return true;
	}
}
