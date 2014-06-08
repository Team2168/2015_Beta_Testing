package org.team2168.robot.commands.subSystems.DriveTrain;

import org.team2168.robot.commands.CommandBase;

public class DriveWithJoystick extends CommandBase {
	
	public DriveWithJoystick(){
		requires(drivetrain);
	}

	
	protected void end() {
		// TODO Auto-generated method stub
	}

	
	protected void execute() {
		drivetrain.tankDrive(oi.getbaseDriverRightAxis(), oi.getbaseDriverLeftAxis());
	}

	
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	
	protected void interrupted() {
		// TODO Auto-generated method stub
	}

	
	protected boolean isFinished() {
		return false;
	}

}
