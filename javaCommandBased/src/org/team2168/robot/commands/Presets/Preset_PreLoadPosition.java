package org.team2168.robot.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.robot.commands.subSystems.Intake.IntakeHopperPosition;
import org.team2168.robot.commands.subSystems.Intake.IntakeStowPosition;
import org.team2168.robot.commands.subSystems.ShooterAngle.ShooterAngleExtend;
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
