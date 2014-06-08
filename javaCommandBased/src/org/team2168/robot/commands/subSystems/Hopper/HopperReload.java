
package org.team2168.robot.commands.subSystems.Hopper;

import org.team2168.robot.CommandBaseRobot;
import org.team2168.robot.commands.CommandBase;

/**
 *
 * @author Shriji
 */
public class HopperReload extends CommandBase {

	/**
	 * Default constructor.
	 */
    public HopperReload() {
    	requires(hopper);
    }

    /**
     * Called just before this Command runs the first time.
     */
    
	protected void initialize() {
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    
	protected void execute() {
		CommandBaseRobot.setDiscFired(false);
    	hopper.reloadDisc();
    	//actuate the piston back to grab another frisbee
    }

    /**
     * Returns true immediately after the stopper has been engaged.
     */
    
	protected boolean isFinished() {
        return true;
    }

    /**
     * Called once after isFinished returns true.
     */
    
	protected void end() {
    	//Nothing special to do here.
    }

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     */
    
	protected void interrupted() {
    	//Nothing special to do here.
    }
}
