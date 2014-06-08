
package frc2168_2013.commands.subSystems.DriveTrain;

import frc2168_2013.commands.CommandBase;

/**
 *
 * @author shriji
 */
public class DrivePIDPause extends CommandBase {

    public DrivePIDPause() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    }

    // Called just before this Command runs the first time
    
	protected void initialize() {
    	drivetrain.rightPosController.Pause();
    	drivetrain.rightSpeedController.Pause();
    	drivetrain.leftPosController.Pause();
    	drivetrain.leftSpeedController.Pause();
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
    	
    }

    //delete me
    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return (drivetrain.rightPosController.isEnabled() == false)
        		&& (drivetrain.rightSpeedController.isEnabled() == false)
        		&& (drivetrain.leftSpeedController.isEnabled() == false)
        		&& (drivetrain.leftPosController.isEnabled() == false);
    }

    // Called once after isFinished returns true
    
	protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    }
}
