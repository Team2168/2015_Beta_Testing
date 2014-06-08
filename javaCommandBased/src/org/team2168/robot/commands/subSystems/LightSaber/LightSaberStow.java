
package frc2168_2013.commands.subSystems.LightSaber;

import frc2168_2013.commands.CommandBase;

/**
 *
 * @author Shriji
 */
public class LightSaberStow extends CommandBase {

    public LightSaberStow() {
    	requires(lightSaber);
    	
    }

    // Called just before this Command runs the first time
    
	protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		lightSaber.stow();
		//retracts the light sabers to maneuver under the pyramid
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
