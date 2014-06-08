package org.team2168.robot.commands.subSystems.ShooterWheel;

import org.team2168.robot.commands.CommandBase;

public class OL_ShooterAtSpeed extends CommandBase
{
	
	private double setpointRPMAft;
	private double setpointRPMFwd;
	
	public OL_ShooterAtSpeed(double RPMAft, double RPMFwd) {
		//This is the Open Loop Shooter At Speed Command... it should block
		//until the wheels are passed some RPM, then end;
	this.setpointRPMAft = RPMAft;
	this.setpointRPMFwd = RPMFwd;
		
	}


	protected void initialize() {
		// TODO Auto-generated method stub


	}


	protected void execute() {
		// TODO Auto-generated method stub

	}


	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return shooterWheel.shooterWheelSpeedControllerAft.getSensorRate() >= this.setpointRPMAft  && shooterWheel.shooterWheelSpeedControllerFwd.getSensorRate() >= this.setpointRPMFwd; 
	}


	protected void end() {
		// TODO Auto-generated method stub

	}


	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}