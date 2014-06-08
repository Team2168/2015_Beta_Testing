package org.team2168.robot.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.robot.commands.subSystems.Intake.IntakeLoadPosition;
import org.team2168.robot.commands.subSystems.ShooterAngle.ShooterAngleStow;
import org.team2168.robot.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

/**
 * Sets the intake and shooter in a position to make it safe to drive under the pyramid.
 */
public class Preset_DriveUnderPyramid extends CommandGroup {
	
	public Preset_DriveUnderPyramid(){

		addSequential(new IntakeLoadPosition());

		//turn shooter wheels on
		addParallel(new DriveShooterWithConstant(0.0, 0.0));

		//lower shooter angle to stow position
		addSequential(new ShooterAngleStow());

		
		
	}
}
