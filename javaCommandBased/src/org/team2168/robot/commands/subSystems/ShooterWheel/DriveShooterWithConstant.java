package org.team2168.robot.commands.subSystems.ShooterWheel;

import org.team2168.robot.commands.CommandBase;




public class DriveShooterWithConstant extends CommandBase {
	
	private double aftWheelSpeed;
	private double fwdWheelSpeed;
	
	/*
	 * Method allows you to drive shooter with constant speed
	 */
	public DriveShooterWithConstant(double aftWheelSpeed, double fwdWheelSpeed){
		requires(shooterWheel);
		
		this.aftWheelSpeed = aftWheelSpeed;
		this.fwdWheelSpeed = fwdWheelSpeed;
	}

	
	protected void end() {
		// TODO Auto-generated method stub
	}

	
	protected void execute() {
		shooterWheel.driveShooterWheels(aftWheelSpeed,fwdWheelSpeed);
		
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
