
package frc2168_2013.commands.subSystems.ShooterWheel;

import frc2168_2013.commands.CommandBase;

/**
 *
 * @author shriji
 */
public class PID_SetFwdWheelSpeed extends CommandBase {
	
	private double setPoint;

    public PID_SetFwdWheelSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(shooterWheel);
    	this.setPoint = shooterWheel.shooterWheelSpeedControllerFwd.getSetPoint();
    }
    
   public PID_SetFwdWheelSpeed(double setPoint){
	   this();
	   this.setPoint = setPoint;
	   
   }

    // Called just before this Command runs the first time
    
	protected void initialize() {
    	shooterWheel.shooterWheelSpeedControllerFwd.reset();
    	shooterWheel.shooterWheelSpeedControllerFwd.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
    	if (setPoint != 0)
    		shooterWheel.shooterWheelSpeedControllerFwd.setSetPoint(setPoint);
    	shooterWheel.driveFwdWheel(shooterWheel.shooterWheelSpeedControllerFwd.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return shooterWheel.shooterWheelSpeedControllerFwd.isEnabled() == false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
    	shooterWheel.shooterWheelSpeedControllerFwd.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}
