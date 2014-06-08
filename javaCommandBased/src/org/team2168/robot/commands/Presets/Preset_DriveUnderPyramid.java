package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.subSystems.Intake.IntakeLoadPosition;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

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
