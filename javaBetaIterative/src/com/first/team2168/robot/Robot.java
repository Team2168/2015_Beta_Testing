package com.first.team2168.robot;

import com.first.team2168.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Talon;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	// Joysticks Objects
	Joystick driver;
	Joystick operator;

	// Drive Moter Object
	Victor leftDrive;
	Victor rightDrive;

	// Drive Shooter Objects
	Talon shooterFWD;
	Talon shooterRear;

	// Shooter Double Solenoid
	static Relay shooterFire;

	// Shooter Angle Solenoid
	static Relay shooterAngle;
	static Relay CompressorRelay;

	// Switches
	DigitalInput CompressorSwitch;

	static DigitalInput DIO1;
	static DigitalInput DIO2;
	static DigitalInput DIO3;
	static DigitalInput DIO4;
	static DigitalInput DIO5;
	static DigitalInput DIO6;
	static DigitalInput DIO7;
	static DigitalInput DIO8;
	static DigitalInput DIO9;
	
	static AnalogChannel AI1;
	static AnalogChannel AI2;
	static AnalogChannel AI3;
	static AnalogChannel AI4;
	
	static Vision cam;
	
	static ConsolePrinter printer;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		// Initialize joysticks
		driver = new Joystick(1);
		operator = new Joystick(2);

		// Initialize drive talons
		rightDrive = new Victor(4);
		leftDrive = new Victor(3);

		// initialize shooter talons
		shooterFWD = new Talon(1);
		shooterRear = new Talon(2);

		// initialize shooter angle
		shooterAngle = new Relay(2, Direction.kBoth);
		shooterFire = new Relay(3, Direction.kBoth);

		CompressorRelay = new Relay(4, Direction.kForward);

		// initialize compressor switch
		CompressorSwitch = new DigitalInput(10);
		
		//Inital All Other IO Pins for smartDashboard
		initHardware();
		
		//init Cam Server
		cam = Vision.getInstance();

		printer = new ConsolePrinter(200);
		printer.startThread();
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		if(Math.abs(driver.getRawAxis(5)) > 0.35) {
			rightDrive.set(driver.getRawAxis(5));
		} else {
			rightDrive.set(0.0);
		}
		if(Math.abs(driver.getRawAxis(2)) > 0.28) {
			leftDrive.set(-driver.getRawAxis(2));
		} else {
			leftDrive.set(0.0);
		}

		if (operator.getRawButton(6)) {
			shooterAngle.set(Value.kForward);
		} else if (operator.getRawButton(5)) {
			shooterAngle.set(Value.kReverse);
		}

		if (operator.getRawButton(2)) {
			shooterFire.set(Value.kForward);
		} else if (operator.getRawButton(1)) {
			shooterFire.set(Value.kReverse);
		}

		//Shooter Wheel - Left stick
		if(operator.getRawAxis(2) <= 0) {
			shooterFWD.set(-operator.getRawAxis(2));
			shooterRear.set(-operator.getRawAxis(2));
		}
		
		
		if (!CompressorSwitch.get()) {
			CompressorRelay.set(Value.kOn);
		} else {
			CompressorRelay.set(Value.kOff);
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}
	
	public void initHardware() {
		
		DIO1 = new DigitalInput(1);
		DIO2 = new DigitalInput(2);
		DIO3 = new DigitalInput(3);
		DIO4 = new DigitalInput(4);
		DIO5 = new DigitalInput(5);
		DIO6 = new DigitalInput(6);
		DIO7 = new DigitalInput(7);
		DIO8 = new DigitalInput(8);
		DIO9 = new DigitalInput(9);
		
		AI1 = new AnalogChannel(1);
		AI2 = new AnalogChannel(2);
		AI3 = new AnalogChannel(3);
		AI4 = new AnalogChannel(4);		
	
	}
}
