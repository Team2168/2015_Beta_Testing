package org.team2168.robot.commands.Presets;

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
import org.team2168.robot.commands.subSystems.ShooterAngle.ShooterAngleStow;
import org.team2168.robot.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

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
