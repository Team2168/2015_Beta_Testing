package org.team2168.robot.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.robot.commands.subSystems.Intake.IntakeLoadPosition;
import org.team2168.robot.commands.subSystems.Intake.IntakeStowPosition;
import org.team2168.robot.commands.subSystems.ShooterAngle.ShooterAngleStow;
import org.team2168.robot.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

/**
 * Sets the intake, shooter and hanger into "hang mode."
 */
public class Preset_10pt_Hang extends CommandGroup {
	
	public Preset_10pt_Hang(){

		addSequential(new IntakeStowPosition());

		//turn shooter wheels on
		addParallel(new DriveShooterWithConstant(0.0, 0.0));

		//lower shooter angle to stow position
		addSequential(new ShooterAngleStow());

		
		
	}
}
