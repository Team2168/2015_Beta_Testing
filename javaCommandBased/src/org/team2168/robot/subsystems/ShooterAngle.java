package org.team2168.robot.subsystems;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.team2168.robot.utils.AlphaDoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2168.robot.RobotMap;

public class ShooterAngle extends Subsystem {
	AlphaDoubleSolenoid actuator;
	
	public ShooterAngle() {
		actuator = new AlphaDoubleSolenoid(RobotMap.shooterRaise,
		                              RobotMap.shooterLower);
	}
	
	/**
	 * Tells the Shooter Angle what to do when it starts
	 */
	protected void initDefaultCommand() {
		
	}
	
	/**
	 * Lower the shooter, for further shots
	 */
	public void stow() {
		actuator.set(AlphaDoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Raise the shooter, for closer shots
	 */
	public void extend() {
		actuator.set(AlphaDoubleSolenoid.Value.kForward);
	}
}
