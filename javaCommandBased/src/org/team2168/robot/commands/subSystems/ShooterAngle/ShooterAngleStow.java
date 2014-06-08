
package frc2168_2013.commands.subSystems.ShooterAngle;

import frc2168_2013.CommandBaseRobot;
import frc2168_2013.commands.CommandBase;

/**
 *
 * @author Shriji
 */
public class ShooterAngleStow extends CommandBase {

    public ShooterAngleStow() {
    	requires(shooterAngle);
    	
    }

    // Called just before this Command runs the first time
    
	protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		CommandBaseRobot.setShooterRaised(false);
		shooterAngle.stow();
    	//sets the shooter angle to stow position
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    
	protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    	
    }
}
