package org.team2168.robot.commands.subSystems.Intake;

import org.team2168.robot.commands.CommandBase;

/**
 * A command to actuate the intake to the stow position
 * 
 * @author Shriji
 *
 */
public class IntakeStowPosition extends CommandBase {

	public IntakeStowPosition() {
		requires(intakePos);
	}

	
	protected void initialize() {
		//Nothing to do
	}

	/**
	 * sets intake to stow position
	 */
	protected void execute() {
		intakePos.stow();
	}

	
	protected void interrupted() {
		//Nothing to do
	}

	
	protected void end() {
		//Nothing to do
	}

	
	protected boolean isFinished() {
		return true;
	}
}
