package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.RobotMap;

public class ShooterAngle extends Subsystem {
	DoubleSolenoid actuator;
	
	public ShooterAngle() {
		actuator = new DoubleSolenoid(RobotMap.shooterRaise,
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
		actuator.set(DoubleSolenoid.Value.kReverse);
	}
	
	/**
	 * Raise the shooter, for closer shots
	 */
	public void extend() {
		actuator.set(DoubleSolenoid.Value.kForward);
	}
}
