package org.team2168.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2168.robot.RobotMap;


public class LightSaber extends Subsystem {
	DoubleSolenoid actuator;
	
	public LightSaber() {
		actuator = new DoubleSolenoid(RobotMap.lightSaberRaise,
				RobotMap.lightSaberLower);
		//start with the light sabers lowered
		stow();
	}
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	
	protected void initDefaultCommand() {

	}
	
	public void extend(){
		actuator.set(DoubleSolenoid.Value.kForward);
	}
    
	public void stow() {
		actuator.set(DoubleSolenoid.Value.kReverse);
	}
}
