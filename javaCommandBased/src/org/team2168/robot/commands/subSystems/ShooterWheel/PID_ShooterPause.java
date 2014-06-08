
package org.team2168.robot.commands.subSystems.ShooterWheel;

import org.team2168.robot.commands.CommandBase;

/**
 *
 * @author shriji
 */
public class PID_ShooterPause extends CommandBase {

    public PID_ShooterPause() {
    	requires(shooterWheel);
    }

    // Called just before this Command runs the first time
   
	protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
  
	protected void execute() {
    	shooterWheel.shooterWheelSpeedControllerAft.Pause();
    	shooterWheel.shooterWheelSpeedControllerFwd.Pause();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    
	protected boolean isFinished() {
        return (shooterWheel.shooterWheelSpeedControllerAft.isEnabled() == false && shooterWheel.shooterWheelSpeedControllerFwd.isEnabled() == false);
    }

    // Called once after isFinished returns true
    
	protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    
	protected void interrupted() {
    }
}
