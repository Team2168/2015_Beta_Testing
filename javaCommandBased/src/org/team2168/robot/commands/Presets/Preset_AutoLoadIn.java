package frc2168_2013.commands.Presets;

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
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

/**
 * Autonomous loading into the hopper.
 * put the intake down, 
 * runs rollers till there are two disks, 
 * raises the intake and dumps them into the hopper, 
 * lowers the intake after.
 * @author Shriji
 *
 */
public class Preset_AutoLoadIn extends CommandGroup {

	public Preset_AutoLoadIn() {
		
		addSequential(new ShooterAngleStow());
		
		addSequential(new IntakeLoadPosition());
		
		addSequential(new DriveIntakeTillFull());

		addSequential(new IntakeHopperPosition());

		addSequential(new Sleep(),2.5);

		addSequential(new DriveLeftTillEmpty());
		
		addSequential(new DriveRightTillEmpty());
		
		addParallel(new DriveIntakeConstant(-1.0,-1.0), .5);
		
		addParallel(new DriveIntakeConstant(0.0,0.0));

		addSequential(new IntakeLoadPosition());


						
	}	
}
