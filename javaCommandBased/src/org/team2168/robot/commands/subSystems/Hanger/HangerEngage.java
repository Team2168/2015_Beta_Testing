package org.team2168.robot.commands.subSystems.Hanger;

import org.team2168.robot.commands.CommandBase;

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
