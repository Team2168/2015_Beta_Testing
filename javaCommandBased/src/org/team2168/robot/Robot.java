/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.team2168.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2168.robot.commands.CommandBase;
import org.team2168.robot.commands.Auto.AutoSequencer;
import org.team2168.robot.commands.subSystems.DriveTrain.DriveDrivetrainTurn_Simple;
import org.team2168.robot.dashboard.CompetitionDashboard;
import org.team2168.robot.utils.BitRelay;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    Command armPositionInit;
    Command dashboard;

    Compressor compressor;
    
    SendableChooser dashChooser;
	static SendableChooser initialPositionChooser;
	static SendableChooser afterShotChooser;
	
	//Delays (seconds) for shots in auto. These get set by the dashboard.
	private static double disc1Delay = 5,
                          disc2Delay = 1.0,
                          disc3Delay = 1.5 ;
	private static final String TIME_1_DELAY_KEY = "Delay before shot 1",
                                TIME_2_DELAY_KEY = "Delay before shot 2",
                                TIME_3_DELAY_KEY = "Delay before shot 3",
                                DRIVETRAIN_RIGHT_ENCODER_KEY = "Right drive encoder",
                                DRIVETRAIN_LEFT_ENCODER_KEY  = "Left drive encoder",
                                DRIVETRAIN_GYRO_ANGLE_KEY    = "Gyro angle",
                                SHOOT_IN_AUTO_KEY            = "Shoot in auto";
	
	//Auto. starting position constants
    public static final int LEFT   = 1,
                            CENTER = 2,
                            RIGHT  = 3;
	
	//What to do in auto. after the discs are shot
	public static final int SIT_STILL                       = 1, //Don't move after shooting
			                FOUR_DISC_AUTO_FRONT_PYRAMID    = 2, //From center, shoot two & two under pyramid
			                FIVE_DISC_AUTO_BACK_PYRAMID     = 3, //From center, shoot three & two under pyramid
	                        DEFEND_CENTER                   = 4, //Move to the center of the field and defend discs
	                        TO_PROTECTED_LOADER             = 5, //Move to the protected human load station
	                        TO_UNPROTECTED_LOADER           = 6; //Move to the unprotected human load station
	
    //These variables are for the serial communication with the arduino.
    private static boolean shooterAtSpeed = false,
                           discFired      = false,
                           shooterRaised  = false,
                           endGame        = false;
    BitRelay lightsRelay1,
             lightsRelay2;
             //lightsRelay3,
             //lightsRelay4;
    
    private static int numberOfDiscs = 3;    //TODO: change this to actually use a sensor
    private static boolean shootInAuto = true;
    private static boolean autoModeDataInitialized = false;
    
    
    /**
     * This method is run when the robot is first started up and should be
     * used for any initialization code.
     */
	public void robotInit() {
        // Initialize all subsystems
        CommandBase.init();
        
        //Start the compressor
        compressor = new Compressor(RobotMap.compressorPressureSwitch, RobotMap.compressorPower);
//        compressor.start();
        
        //Initialize dashboard
        dashSelectInit();
        
    	//Put auto. mode data & selections onto the dashboard
        putAutoModeData();
        
        //Initialize relay ports for light strip states
        lightsRelay1 = new BitRelay(RobotMap.arduinoRelay1);
        lightsRelay2 = new BitRelay(RobotMap.arduinoRelay3);
        //lightsRelay3 = new BitRelay(RobotMap.arduinoRelay3);
        //lightsRelay4 = new BitRelay(RobotMap.arduinoRelay4);
        
        //End of Robot Init
    	System.out.println("ROBOT FINISHED LOADING!");
    }

    /**
     * This method is called once, when the robot first enters auto mode.
     */
	public void autonomousInit() {
    	Scheduler.getInstance().enable();

    	//Get delays for disc shots
    	disc1Delay = SmartDashboard.getNumber(TIME_1_DELAY_KEY, disc1Delay);
    	disc2Delay = SmartDashboard.getNumber(TIME_2_DELAY_KEY, disc2Delay);
    	disc3Delay = SmartDashboard.getNumber(TIME_3_DELAY_KEY, disc3Delay);

    	shootInAuto = SmartDashboard.getBoolean(SHOOT_IN_AUTO_KEY, shootInAuto);
    	
    	//Get parameters from dashboard for auto mode turning.
    	DriveDrivetrainTurn_Simple.setFastSpeed(SmartDashboard.getNumber("Auto. turn fast speed",
    			DriveDrivetrainTurn_Simple.getFastSpeed()));
    	DriveDrivetrainTurn_Simple.setSlowSpeed(SmartDashboard.getNumber("Auto. turn slow speed",
    			DriveDrivetrainTurn_Simple.getSlowSpeed()));
    	
    	// instantiate and schedule the command used for the autonomous period
        autonomousCommand = new AutoSequencer();
        autonomousCommand.start();
        
        //start dashboard
        dashboard = (Command) dashChooser.getSelected();
        dashboard.start();
        
        setArduinoStatus();
    }

    /**
     * This method is called periodically during autonomous
     */
	public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        //putAutoModeData();
        
        setArduinoStatus();
    }

    /**
     * 
     * This method is called once, when the robot first enters teleop mode.
     */
    
	public void teleopInit() {
    	Scheduler.getInstance().enable();
    	// This makes sure that the autonomous stops running when teleop starts
    	// running. If you want the autonomous to continue until interrupted by
    	// another command, remove this line or comment it out.
    	if(autonomousCommand != null)
    		autonomousCommand.cancel();
    	
        //start dashboard
        dashboard = (Command) dashChooser.getSelected();
        dashboard.start();
        
        compressor.start();
        
        setArduinoStatus();
    }

    /**
     * This method is called periodically during operator control
     */
    
	public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	
    	if(DriverStation.getInstance().getMatchTime() >= 115){
    		setEndGame(true);
    	}
    	
        //Update drivetrain position fields on dashboard
        SmartDashboard.putNumber(DRIVETRAIN_RIGHT_ENCODER_KEY, CommandBase.getDrivetrainInstance().getRightDistance());
        SmartDashboard.putNumber(DRIVETRAIN_LEFT_ENCODER_KEY, CommandBase.getDrivetrainInstance().getLeftDistance());
        SmartDashboard.putNumber(DRIVETRAIN_GYRO_ANGLE_KEY, CommandBase.getDrivetrainInstance().getAngle());
    	
    	setArduinoStatus();
    }
    
    /**
     * This method is called periodically during test mode
     */
    
	public void testPeriodic() {
        LiveWindow.run();
    }
    
    /**
     * This method is called once, the first time the robot gets disabled. 
     */
    
	public void disabledInit() {
    	//Kill all active commands and make sure new ones don't run.
    	Scheduler.getInstance().removeAll();
    	Scheduler.getInstance().disable();
    	
    	//Automatically retract the hanger, buzzer beater.
    	// Note: this is done after the schedule is killed (there will be no
    	// active commands running) so it's ok in this case to access the
    	// subsystem's methods directly.
    	//TODO: verify this works as expected
    	CommandBase.getHangerInstance().engage();
    	
    	//SerialCommunicator.free();
    }
    
    /**
     * This method gets called repeatedly while the robot is disabled.
     */
	public void disabledPeriodic() {
		//Update the dashboard selection options
		//putAutoModeData();
    }
    
	/**
	 * Add dashboard selection options to the smart dashboard.
	 */
    private void dashSelectInit() {
        dashChooser = new SendableChooser();
        dashChooser.addDefault("Competition Dash", new CompetitionDashboard());
        //dashChooser.addObject("PID Debug Dash", new ShooterWheelPIDDashboard());
        SmartDashboard.putData("Dashboard Chooser", dashChooser);
    }
    
    /**
     * Initialize the auto mode selection/customization options.
     */
    private static void initAutoModeData() {
    	//If we haven't already instantiated our variables, do so
    	if(!autoModeDataInitialized) {
    		//Create a chooser for our starting position
        	initialPositionChooser = new SendableChooser();
        	initialPositionChooser.addObject("Left", new Integer(LEFT));
        	initialPositionChooser.addDefault("Center", new Integer(CENTER));
        	initialPositionChooser.addObject("Right", new Integer(RIGHT));
    		
    		//Create a chooser for our destination position
    		afterShotChooser = new SendableChooser();
        	afterShotChooser.addDefault("Sit Still", new Integer(SIT_STILL));
        	afterShotChooser.addObject("4 disc auto from the front (closer to the wall)", new Integer(FOUR_DISC_AUTO_FRONT_PYRAMID));
        	afterShotChooser.addObject("5 disc auto from the back (farther from the wall)", new Integer(FIVE_DISC_AUTO_BACK_PYRAMID));
        	afterShotChooser.addObject("Defend center discs", new Integer(DEFEND_CENTER));
        	afterShotChooser.addObject("Move to protected loader", new Integer(TO_PROTECTED_LOADER));
        	afterShotChooser.addObject("Move to un-protected loader", new Integer(TO_UNPROTECTED_LOADER));
    		
    		//Mark auto select variables as initialized (only do once)
    		autoModeDataInitialized = true;
    	}
    }
    
    /**
     * Put the auto. mode selection/customization options onto the dashboard
     * 
     * This can be called periodically.
     */
	private static void putAutoModeData() {
    	//If variables haven't been instantiated yet, do so.
    	if(!autoModeDataInitialized) {
    		initAutoModeData();
    	}
    	 
    	//Add a radfio button lsit to the dashboard to choose our initial position
    	SmartDashboard.putData("Start_Position", initialPositionChooser);
    	
    	//Add a radio button list to the dashboard to choose our destination position
    	SmartDashboard.putData("Dest_Position", afterShotChooser);
    	
    	//TODO: verify if we can still change fields if we're periodically updating them through this method
    	//TODO: Will probably need to call the get method periodically as well so we don't overwrite it.
    	//Put our timing data for shots 
    	SmartDashboard.putNumber(TIME_1_DELAY_KEY, disc1Delay);
    	SmartDashboard.putNumber(TIME_2_DELAY_KEY, disc2Delay);
    	SmartDashboard.putNumber(TIME_3_DELAY_KEY, disc3Delay);
    	
    	SmartDashboard.putNumber("Auto. turn fast speed", DriveDrivetrainTurn_Simple.getFastSpeed());
    	SmartDashboard.putNumber("Auto. turn slow speed", DriveDrivetrainTurn_Simple.getSlowSpeed());
    	
    	//Update drivetrain position fields on dashboard
        SmartDashboard.putNumber(DRIVETRAIN_RIGHT_ENCODER_KEY, CommandBase.getDrivetrainInstance().getRightDistance());
        SmartDashboard.putNumber(DRIVETRAIN_LEFT_ENCODER_KEY, CommandBase.getDrivetrainInstance().getLeftDistance());
        SmartDashboard.putNumber(DRIVETRAIN_GYRO_ANGLE_KEY, CommandBase.getDrivetrainInstance().getAngle());
        
        SmartDashboard.putBoolean("Shoot in auto", shootInAuto);
    }
    
    /**
     * Get the initial position of the robot as selected on the dashboard
     * @return the initial position
     */
    public static int getInitialPosition() {
    	return ((Integer) initialPositionChooser.getSelected()).intValue();
    }
    
    /**
     * The action the robot should take after it shoots its discs in auto mode
     * @return the action to perform
     */
    public static int getAutoModeAfterShots() {
    	return ((Integer) afterShotChooser.getSelected()).intValue();
    }
    
    /**
     * The time to delay before shooting the first disc in auto
     * @param disc the disc  to get the delay for (1 - 3)
     * @return time in seconds
     */
    public static double getDiscDelay(int disc) {
    	double retVal = 0.0;
    	
    	switch(disc) {
	    	case 1:
	    		retVal = disc1Delay;
	    		break;
	    	case 2:
	    		retVal = disc2Delay;
	    		break;
	    	case 3:
	    		retVal = disc3Delay;
	    		break;
	    	default:
	    		//Delay returned will be the default value defined above
	    		break;
    	}
    	
    	return retVal;
    }
    
    /**
     * Get, from the dashboard option, whether or not we should shoot our discs in auto.
     * This is in an effort to defend the center line quicker if necessary.
     * 
     * @return true if we should shoot discs in auto.
     */
    public static boolean shootInAuto() {
    	return shootInAuto;
    }
    
    /**
     * This method handles outputting the data to the arduino for lighting
     */
    void setArduinoStatus() {
    	// COMMUNICATION PROTOCOL - BITMAP
    	// BIT(S)     Meaning
    	// ------------------------------
    	//   0        Shooter raised when true
    	//   1        Shooter up to speed
    	//   3        Disc fired
    	//   4        Endgame (end of match notification)
    	lightsRelay1.setForward(shooterRaised);
    	lightsRelay1.setReverse(shooterAtSpeed);
    	lightsRelay2.setForward(discFired);
    	lightsRelay2.setReverse(endGame);

    	// BIT(S)     Meaning
    	// ------------------------------
    	// 0 - 2      # discs (0 - 4)
    	//   3        Shooter up to speed
    	//   4        Disc fired
    	//   5        Against bar
    	//   6        Endgame (last 30 sec)
    	//   7        Autonomous mode
    	
    	//Set the number of discs
//    	switch(numberOfDiscs) {
//    		case 0:
//    			lightsRelay1.set(Relay.Value.kOff);
//    			lightsRelay2.setForward(false);
//    			break;
//    		case 1:
//    			lightsRelay1.set(Relay.Value.kForward);
//    			lightsRelay2.setForward(false);
//    			break;
//    		case 2:
//    			lightsRelay1.set(Relay.Value.kReverse);
//    			lightsRelay2.setForward(false);
//    			break;
//    		case 3:
//    			lightsRelay1.set(Relay.Value.kOn);
//    			lightsRelay2.setForward(false);
//    			break;
//    		default:
//    			lightsRelay1.set(Relay.Value.kOff);
//    			lightsRelay2.setForward(true);
//    	}
//    	
//    	//Set remaining flag for lights
//    	lightsRelay2.setReverse(shooterAtSpeed);
//    	lightsRelay3.setForward(discFired);
//    	lightsRelay3.setReverse(againstBar);
//    	lightsRelay4.setForward(endGame);
//    	lightsRelay4.setReverse(autoMode);
//    	
//    	System.out.println(numberOfDiscs);
    }

	/**
	 * @param shooterAtSpeed true if at speed
	 */
	public static void setShooterAtSpeed(boolean value) {
		shooterAtSpeed = value;
	}

	/**
	 * @param discFired true if disc fired
	 */
	public static void setDiscFired(boolean value) {
		discFired = value;
	}

	/**
	 * @param endGame true if in the end of the game
	 */
	public static void setEndGame(boolean value) {
		endGame = value;
	}

	/**
	 * @param value true if the shooter is raised
	 */
	public static void setShooterRaised(boolean value) {
		shooterRaised = value;
	}
}
