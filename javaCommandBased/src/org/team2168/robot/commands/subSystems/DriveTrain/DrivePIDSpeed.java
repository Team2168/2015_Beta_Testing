
package frc2168_2013.commands.subSystems.DriveTrain;

import frc2168_2013.commands.CommandBase;

/**
 *
 * @author shriji
 */
public class DrivePIDSpeed extends CommandBase {
	
	private double setPoint;

    public DrivePIDSpeed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	this.setPoint = drivetrain.rightSpeedController.getSetPoint();
    }
    
   public DrivePIDSpeed(double setPoint){
	   this();
	   this.setPoint = setPoint;
	   
   }

    // Called just before this Command runs the first time
    
	protected void initialize() {
    	drivetrain.rightSpeedController.reset();
    	drivetrain.rightSpeedController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
    	if (setPoint != 0)
    		drivetrain.rightSpeedController.setSetPoint(setPoint);
    	drivetrain.driveRight(drivetrain.rightSpeedController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return drivetrain.rightSpeedController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
    	drivetrain.rightSpeedController.Pause();
    }

    //delete me
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}
