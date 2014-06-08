package org.team2168.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.robot.commands.Sleep;
import org.team2168.robot.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import org.team2168.robot.commands.subSystems.Hopper.ShootSingleDisc;
import org.team2168.robot.commands.subSystems.Intake.DriveIntakeConstant;
import org.team2168.robot.commands.subSystems.Intake.DriveIntakeTillFull;
import org.team2168.robot.commands.subSystems.Intake.DriveLeftTillEmpty;
import org.team2168.robot.commands.subSystems.Intake.DriveRightTillEmpty;
import org.team2168.robot.commands.subSystems.Intake.IntakeHopperPosition;
import org.team2168.robot.commands.subSystems.Intake.IntakeLoadPosition;
import org.team2168.robot.commands.subSystems.Intake.IntakeStowPosition;
import org.team2168.robot.commands.subSystems.ShooterAngle.ShooterAngleExtend;
import org.team2168.robot.commands.subSystems.ShooterAngle.ShooterAngleStow;
import org.team2168.robot.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

/**
 * Auto command. Drives back to lower intake then drives forward; shoots three discs; 
 * drives forward to pick up disks. Drives back and shoots.
 * FROM BACK OF THE PYRAMID (FARTHER FROM THE WALL)
 * @author Shriji
 *
 */
public class FiveDisc_3pt_BackPyramid extends CommandGroup {

	public FiveDisc_3pt_BackPyramid() {
		
		addSequential(new ShooterAngleStow());
				
		addParallel(new DriveShooterWithConstant(1, 1));

		addSequential(new DriveDrivetrainStraight(-30.0));

		addSequential(new IntakeLoadPosition());

		addSequential(new Sleep(),1.0);

		addSequential(new DriveDrivetrainStraight(30.0));

		//Shoot three discs
		addSequential(new Sleep(), 1);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), 1);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), 1);
		addSequential(new ShootSingleDisc());
		
		addParallel(new DriveIntakeTillFull());

		addSequential(new DriveDrivetrainStraight(30.0));
		
		addSequential(new IntakeHopperPosition());

		addSequential(new Sleep(),2.5);

		addSequential(new DriveLeftTillEmpty());
		
		addSequential(new DriveRightTillEmpty());
		
		addParallel(new DriveIntakeConstant(-1.0,-1.0), .5);
		
		addParallel(new DriveIntakeConstant(0.0,0.0));

		addSequential(new IntakeLoadPosition());

		addSequential(new DriveDrivetrainStraight(-30.0));


		//Shoot two more discs
		addSequential(new Sleep(), 2);
		addSequential(new ShootSingleDisc());
		addSequential(new Sleep(), 1);
		addSequential(new ShootSingleDisc());
						
	}	
}
