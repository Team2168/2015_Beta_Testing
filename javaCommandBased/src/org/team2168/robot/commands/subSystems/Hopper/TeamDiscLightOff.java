package frc2168_2013.commands.subSystems.Hopper;

import frc2168_2013.commands.CommandBase;

/**
 * A command to turn on the team disc light.
 */
public class TeamDiscLightOff extends CommandBase {

	public TeamDiscLightOff() {
		//Doesn't require any subsystem.
	}

	
	protected void initialize() {
		//Nothing to do
	}

	
	protected void execute() {
		hopper.setDiscLightOff();
	}

	
	protected boolean isFinished() {
		return true;
	}
	
	
	protected void interrupted() {
		//Nothing to do
	}

	
	protected void end() {
		//Nothing to do
	}
}
