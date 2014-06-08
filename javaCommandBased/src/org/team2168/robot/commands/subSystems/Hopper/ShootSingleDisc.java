package frc2168_2013.commands.subSystems.Hopper;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.Sleep;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class ShootSingleDisc extends CommandGroup {
	public ShootSingleDisc() {
		//addSequential(new OL_ShooterAtSpeed(RobotMap.AFT_SHOOTERWHEEL_BACK_PYRAMID_3PT_SPEED,RobotMap.FWD_SHOOTERWHEEL_BACK_PYRAMID_3PT_SPEED));

		addSequential(new HopperReload());
		addSequential(new Sleep(),0.15);
		addSequential(new HopperFire());
		addSequential(new Sleep(),0.15);
		addSequential(new HopperReload());

		
		
	}
}
