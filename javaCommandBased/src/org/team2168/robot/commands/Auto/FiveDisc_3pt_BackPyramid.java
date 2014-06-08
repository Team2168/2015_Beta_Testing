package frc2168_2013.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.Sleep;
import frc2168_2013.commands.subSystems.DriveTrain.DriveDrivetrainStraight;
import frc2168_2013.commands.subSystems.Hopper.ShootSingleDisc;
import frc2168_2013.commands.subSystems.Intake.DriveIntakeConstant;
import frc2168_2013.commands.subSystems.Intake.DriveIntakeTillFull;
import frc2168_2013.commands.subSystems.Intake.DriveLeftTillEmpty;
import frc2168_2013.commands.subSystems.Intake.DriveRightTillEmpty;
import frc2168_2013.commands.subSystems.Intake.IntakeHopperPosition;
import frc2168_2013.commands.subSystems.Intake.IntakeLoadPosition;
import frc2168_2013.commands.subSystems.Intake.IntakeStowPosition;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleExtend;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

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
