package frc2168_2013.commands.Presets;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc2168_2013.commands.subSystems.Intake.IntakeLoadPosition;
import frc2168_2013.commands.subSystems.ShooterAngle.ShooterAngleExtend;
import frc2168_2013.commands.subSystems.ShooterWheel.DriveShooterWithConstant;

/**
 * Shots from against the pyramid, closest to the goal
 */
public class Preset_FrontOfPyramid_3pt extends CommandGroup {
	
	public Preset_FrontOfPyramid_3pt(){
		
		addSequential(new IntakeLoadPosition());
		
		//turn shooter wheels on
		addParallel(new DriveShooterWithConstant(1, 1));
				
		//lower shooter angle to stow position
		addSequential(new ShooterAngleExtend());
				
//		//Get wheel up to speed
//		addParallel(new PID_SetAftWheelSpeed(RobotMap.FRONT_PYRAMID_3PT_SPEED));
//		addParallel(new PID_SetFwdWheelSpeed(RobotMap.FRONT_PYRAMID_3PT_SPEED));
	}
}
