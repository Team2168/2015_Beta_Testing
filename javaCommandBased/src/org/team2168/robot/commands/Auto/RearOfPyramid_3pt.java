package org.team2168.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.robot.Robot;
import org.team2168.robot.commands.Sleep;
import org.team2168.robot.commands.subSystems.Hopper.ShootSingleDisc;
import org.team2168.robot.commands.subSystems.ShooterAngle.ShooterAngleStow;
import org.team2168.robot.commands.subSystems.ShooterWheel.DriveShooterWithConstant;
import org.team2168.robot.commands.subSystems.ShooterWheel.PID_ShooterPause;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class RearOfPyramid_3pt extends CommandGroup {

	public RearOfPyramid_3pt() {
		this(Robot.getDiscDelay(1),
             Robot.getDiscDelay(2),
             Robot.getDiscDelay(3));
	}

	public RearOfPyramid_3pt(double firstDiscTime, double secondDiscTime, double thirdDiscTime) {
		//set shooter angle to stow position
		addParallel(new ShooterAngleStow());

		//driver both shooterwheels at full speed
		addParallel(new DriveShooterWithConstant(1, 1));

		//Shoot three discs
		addSequential(new Sleep(), firstDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), secondDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), thirdDiscTime);
		addSequential(new ShootSingleDisc());

//		//using at speed
//		addSequential(new PID_ShooterAtSpeed());
//		addSequential(new ShootSingleDisc());
//		addSequential(new PID_ShooterAtSpeed());
//		addSequential(new ShootSingleDisc());
//		addSequential(new PID_ShooterAtSpeed());
//		addSequential(new ShootSingleDisc());
//		
		//Shoot a few more times in case the disc wasn't shot
		addSequential(new Sleep(), 0.1);
		addSequential(new ShootSingleDisc());

		//Stop the shooter
		addSequential(new Sleep(), 0.2);
		addSequential(new PID_ShooterPause());
	}
}