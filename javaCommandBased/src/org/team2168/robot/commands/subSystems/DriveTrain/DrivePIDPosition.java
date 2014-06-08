
package frc2168_2013.commands.subSystems.DriveTrain;

import frc2168_2013.commands.CommandBase;

/**
 *
 * @author shriji
 */
public class DrivePIDPosition extends CommandBase {

	private double setPoint;
	
    public DrivePIDPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drivetrain);
    	this.setPoint = drivetrain.leftPosController.getSetPoint();
    	
    }

    public DrivePIDPosition(double setPoint){
 	   this();
 	   this.setPoint = setPoint;
    }

    //delete me
    
    
    // Called just before this Command runs the first time
    
	protected void initialize() {
    	drivetrain.leftPosController.reset();
    	drivetrain.leftPosController.Enable();
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
    	drivetrain.leftPosController.setSetPoint(setPoint);
    	drivetrain.driveLeft(drivetrain.leftPosController.getControlOutput());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
    	return drivetrain.leftPosController.isEnabled() == false;
    }

    // Called once after isFinished returns true
    
	protected void end() {
    	drivetrain.leftPosController.Pause();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	end();
    }
}
