
package org.team2168.robot.commands.subSystems.Hopper;

import org.team2168.robot.Robot;
import org.team2168.robot.commands.CommandBase;

/**
 *
 * @author Shriji
 */
public class HopperFire extends CommandBase {

    public HopperFire() {
    	requires(hopper);
    	
    }

    // Called just before this Command runs the first time
    
	protected void initialize() {
		
    }

    // Called repeatedly when this Command is scheduled to run
    
	protected void execute() {
		Robot.setDiscFired(true);
		hopper.fireDisc();
    	//actuate the piston to fire which feeds a frisbee into the shooterwheels
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
