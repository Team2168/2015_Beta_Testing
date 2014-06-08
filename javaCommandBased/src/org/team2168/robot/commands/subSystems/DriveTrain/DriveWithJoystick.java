package frc2168_2013.commands.subSystems.DriveTrain;

import frc2168_2013.commands.CommandBase;

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
