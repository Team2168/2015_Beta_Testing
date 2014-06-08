
package frc2168_2013.commands.subSystems.ShooterWheel;

import frc2168_2013.commands.CommandBase;

/**
 *
 * @author shriji
 */
public class PID_SetAftWheelSpeed extends CommandBase {
	
	private double setPoint;

    public PID_SetAftWheelSpeed() {
    	requires(shooterWheel);
    	this.setPoint = shooterWheel.shooterWheelSpeedControllerAft.getSetPoint();
    }
    
   public PID_SetAftWheelSpeed(double setPoint){
	   this();
	   this.setPoint = setPoint;
	   
   }

    // Called just before this Command runs the first time
    
	protected void initialize() {
    	shooterWheel.shooterWheelSpeedControllerAft.reset();
    	shooterWheel.shooterWheelSpeedControllerAft.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
    	if (setPoint != 0)
    		shooterWheel.shooterWheelSpeedControllerAft.setSetPoint(setPoint);
    	shooterWheel.driveAftWheel(shooterWheel.shooterWheelSpeedControllerAft.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return shooterWheel.shooterWheelSpeedControllerAft.isEnabled() == false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
    	shooterWheel.shooterWheelSpeedControllerAft.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}
