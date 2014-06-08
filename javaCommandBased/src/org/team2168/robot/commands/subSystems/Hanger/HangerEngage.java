package frc2168_2013.commands.subSystems.Hanger;

import frc2168_2013.commands.CommandBase;

/**
 * A command to engage the hanger mechanism.
 * 
 * @author ICW
 *
 */
public class HangerEngage extends CommandBase {

	public HangerEngage() {
		requires (hanger);
	}

	
	protected void initialize() {
		//Nothing to do
	}

	
	protected void execute() {
		hanger.engage();
		//pulls the hanger down to lift off the ground
	}

	
	protected void interrupted() {
		//Nothing to do
	}

	
	protected void end() {
		//Nothing to do
	}

	
	protected boolean isFinished() {
		//Keep engaging the hanger until another command come along that
		// requires the hanger.
		return false;
	}
}
