package org.team2168.robot.subsystems;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.team2168.robot.utils.AlphaDoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2168.robot.RobotMap;


public class Hopper extends Subsystem {

	AlphaDoubleSolenoid actuator;
//	Relay teamDiscLight;
	
	public Hopper() {
//		teamDiscLight = new Relay(RobotMap.teamDiscLight);
		
		actuator = new AlphaDoubleSolenoid(RobotMap.hopperReload,
                RobotMap.hopperFire);
		
		//Start with actuator ready to fire a disc (reloaded)
		reloadDisc();
	}
	
	/**
	 * Bring a disc into the shooter wheels
	 */
	public void fireDisc() {
		actuator.set(AlphaDoubleSolenoid.Value.kReverse);
	}
	 
	/**
	 * Reload the shooter piston in preparation for firing.
	 */
	public void reloadDisc() {
		actuator.set(AlphaDoubleSolenoid.Value.kForward);
	}
	
	protected void initDefaultCommand() {
		//TODO: Add default command.

	}
	 
	 /**
	  * Turn on the team disc light indicator.
	  */
//	 public void setDiscLightOn() {
//		 //Apply 12V to the + spike output, and gnd to the - spike output
//		 teamDiscLight.set(Relay.Value.kForward);
//	 }
//	 
//	 /**
//	  * Turn off the team disc light indicator.
//	  */
//	 public void setDiscLightOff() {
//		 teamDiscLight.set(Relay.Value.kOff);
//	 }
}
