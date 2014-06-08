package org.team2168.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2168.robot.RobotMap;

public class Hanger extends Subsystem {
	DoubleSolenoid actuator;
	
	public Hanger() {
		actuator = new DoubleSolenoid(RobotMap.hangerDisengage,
		                              RobotMap.hangerEngage);
		//start with the hanger lowered
		engage();
	}
	
	/**
	 * Tells the hanger what to do when it starts
	 */
	protected void initDefaultCommand() {
		//Hanger shouldn't move, no default command. stay where you are hanger!
	}
	
	/**
	 * Engage the hanger / retract the actuators.
	 */
	public void engage() {
		//TODO: Verify that kForward engages the hanger
		//pull the hanger down to lift off the ground
		actuator.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Disengage the hanger / extend the actuators
	 */
	public void disengage(){
		//TODO: Verify that kForward disengages the hanger
		//puts the hanger up to grab the bar
		actuator.set(DoubleSolenoid.Value.kForward);
	}
}
