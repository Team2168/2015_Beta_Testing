package org.team2168.robot.commands.Auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.team2168.robot.CommandBaseRobot;

/**
 * The auto command. Kicks off a number of different auto modes, depending on
 * what the operator has selected for an initial position and a destination
 * position on the dashboard.
 * 
 * @author James
 *
 */
public class AutoSequencer extends CommandGroup {

	public AutoSequencer() {
		//System.out.println("Auto mode after shots:" + CommandBaseRobot.getAutoModeAfterShots());
		switch (CommandBaseRobot.getAutoModeAfterShots()) {
			/*
			 * The five disc auto command ignores the starting position selected on the dashboard.
			 * It only works from the center position.
			 */
			case CommandBaseRobot.FOUR_DISC_AUTO_FRONT_PYRAMID:
				addSequential(new FourDisc_3pt_FrontPyramid());
				break;
				
			case CommandBaseRobot.FIVE_DISC_AUTO_BACK_PYRAMID:
				addSequential(new FiveDisc_3pt_BackPyramid());
				break;

			/*
			 * All the following commands use the two sendable choosers on the dashboard.
			 * They will start by sitting still and shooting the three discs loaded.
			 * Then move to the selected destination position.
			 */
			case CommandBaseRobot.DEFEND_CENTER:
				//Fast defend
				if(CommandBaseRobot.shootInAuto()) {
					//Sit still and shoot the discs we started with
					addSequential(new RearOfPyramid_3pt());
				}
				addSequential(new DriveToFieldCenter());
				break;
			case CommandBaseRobot.TO_PROTECTED_LOADER:
				if(CommandBaseRobot.shootInAuto()) {
					addSequential(new RearOfPyramid_3pt());
				}
				addSequential(new DriveToProtectedLoader());
				break;
			case CommandBaseRobot.TO_UNPROTECTED_LOADER:
				if(CommandBaseRobot.shootInAuto()) {
					addSequential(new RearOfPyramid_3pt());
				}
				addSequential(new DriveToUnprotectedLoader());
				break;
			default:
				if(CommandBaseRobot.shootInAuto()) {
					addSequential(new RearOfPyramid_3pt());
				}
				//Then sit still, do nothing
				break;
		}
	}
}
