package org.team2168.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.robot.CommandBaseRobot;
import org.team2168.robot.commands.Sleep;
import org.team2168.robot.commands.subSystems.Hopper.ShootSingleDisc;
import org.team2168.robot.commands.subSystems.ShooterAngle.ShooterAngleExtend;
import org.team2168.robot.commands.subSystems.ShooterWheel.DriveShooterWithConstant;
import org.team2168.robot.commands.subSystems.ShooterWheel.PID_ShooterPause;

/**
 * Auto command. Sits still and shoots discs from close range at the three point goal.
 * 
 * @author James
 *
 */
public class FrontOfPyramid_3pt extends CommandGroup {

	public FrontOfPyramid_3pt() {
		this(CommandBaseRobot.getDiscDelay(1),
             CommandBaseRobot.getDiscDelay(2));
	}

	public FrontOfPyramid_3pt(double firstDiscTime, double secondDiscTime) {
		//set shooter angle to extend position
		addParallel(new ShooterAngleExtend());

		//driver both shooterwheels at full speed
		addParallel(new DriveShooterWithConstant(1, 1));

		//Shoot three discs
		addSequential(new Sleep(), firstDiscTime);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), secondDiscTime);
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
		//addSequential(new Sleep(), 0.1);
		//addSequential(new ShootSingleDisc());

		//Stop the shooter
		addSequential(new Sleep(), 0.2);
		addSequential(new PID_ShooterPause());
	}
}