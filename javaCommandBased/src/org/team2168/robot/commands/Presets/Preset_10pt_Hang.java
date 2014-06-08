package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.subSystems.Intake.IntakeLoadPosition;
import frc2168_2013.commands.subSystems.Intake.IntakeStowPosition;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleStow;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

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
