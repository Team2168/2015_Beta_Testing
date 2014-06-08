package org.team2168.robot.commands.subSystems.ShooterWheel;

import org.team2168.robot.commands.CommandBase;


public class DriveShooterWithJoystick extends CommandBase {
	
	public DriveShooterWithJoystick(){
		requires(shooterWheel);
	}

	
	protected void end() {
		// TODO Auto-generated method stub
	}

	
	protected void execute() {
		//drive both shooterwheels using the left axis on operator joystick
		
		shooterWheel.driveShooterWheels(oi.getoperatorDriveLeftStick(),oi.getoperatorDriveLeftStick());
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
