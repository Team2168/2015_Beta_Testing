package org.team2168.robot.commands.subSystems.Hopper;

import org.team2168.robot.commands.CommandBase;

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
