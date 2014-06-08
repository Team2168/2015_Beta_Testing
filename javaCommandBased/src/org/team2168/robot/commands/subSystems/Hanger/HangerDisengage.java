package org.team2168.robot.commands.subSystems.Hanger;

import org.team2168.robot.commands.CommandBase;

/**
 * A command to disengage the hanger mechanism.
 * 
 * @author ICW
 *
 */
public class HangerDisengage extends CommandBase {

	public HangerDisengage() {
		requires (hanger);
	}

	
	protected void initialize() {
		//Nothing to do here
	}

	
	protected void execute() {
		hanger.disengage();
		//pulls the hanger up to grab the bar
	}

	
	protected void interrupted() {
		//Nothing to do here
	}

	
	protected void end() {
		//Nothing to do here
	}

	
	protected boolean isFinished() {
		//Keep disengaging the hanger until another command come along that
		// requires the hanger.
		return false;
	}
}
