package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.subSystems.Intake.IntakeHopperPosition;
import frc2168_2013.commands.subSystems.Intake.IntakeStowPosition;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleExtend;
/**
 * Sets the intake to load hopper position
 * its easier to drive robot around when in this position than in stow.
 */
public class Preset_PreLoadPosition extends CommandGroup {
	
	public Preset_PreLoadPosition(){

		addSequential(new IntakeHopperPosition());

		//lower shooter angle to stow position
		addSequential(new ShooterAngleExtend());

		
	}
}
