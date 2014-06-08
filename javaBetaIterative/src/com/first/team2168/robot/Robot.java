package com.first.team2168.robot;

import com.first.team2168.robot.sensors.TCPCameraSensor;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PCMCompressor;

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
	//static Relay shooterFire;
	static Solenoid shooterFire1;
	static Solenoid shooterFire2;
	
	// Shooter Angle Solenoid
	//static Relay shooterAngle;
	static Solenoid shooterAngle1;
	static Solenoid shooterAngle2;
	
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
	
	static TCPCameraSensor cam;
	
	static ConsolePrinter printer;
	
	static String cmdAngleShooter;
	static String l_cmdAngleShooter;
	static PCMCompressor m_pcm;
	
	static boolean fired;

	static Relay r1;
	
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
		shooterAngle1 = new Solenoid(7);
		shooterAngle2 = new Solenoid(8);
		
		shooterFire1 = new Solenoid(6);
		shooterFire2 = new Solenoid(5);

		//CompressorRelay = new Relay(4, Direction.kForward);

		// initialize compressor switch
		CompressorSwitch = new DigitalInput(10);
		
		//Inital All Other IO Pins for smartDashboard
		initHardware();
		
		cam = new TCPCameraSensor(1111, 200);
		cam.start();

		printer = new ConsolePrinter(200);
		printer.startThread();
		
		cmdAngleShooter = "down";
		l_cmdAngleShooter = "down";

		m_pcm = new PCMCompressor();
		m_pcm.setClosedLoopControl(true);
		
		r1 = new Relay(1);
		
		
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
			shooterAngle1.set(true);
			shooterAngle2.set(false);
			l_cmdAngleShooter = "kReverse";
			cmdAngleShooter = "kForward";
			
		} else if (operator.getRawButton(5)) {
			shooterAngle1.set(false);
			shooterAngle2.set(true);
			l_cmdAngleShooter = "kForward";
			cmdAngleShooter = "kReverse";
		}

		if (operator.getRawButton(2)) {
			shooterFire1.set(true);
			shooterFire2.set(false);
		} else if (operator.getRawButton(1)) {
			shooterFire1.set(false);
			shooterFire2.set(true);
		}
		
		//Shooter Wheel - Left stick
		if(operator.getRawAxis(2) <= 0) {
			shooterFWD.set(-operator.getRawAxis(2));
			shooterRear.set(-operator.getRawAxis(2));
		}
		
		
		//if (!CompressorSwitch.get()) {
		//	CompressorRelay.set(Value.kOn);
		//} else {
		//	CompressorRelay.set(Value.kOff);
		//}
		
		if (operator.getRawButton(3)) {
			r1.set(Relay.Value.kForward);
		} else if (operator.getRawButton(4)) {
			r1.set(Relay.Value.kOff);
		}

		if(isOperatorControl()){
			if(!m_pcm.getPressureSwitchValue()){
				System.out.println("Low pressure - turning on the compressor");
			}
			System.out.println("The compressor using " + (m_pcm.getCompressorCurrent())/1000 + " Amps");
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
