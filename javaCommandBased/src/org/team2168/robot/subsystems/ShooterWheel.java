package org.team2168.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2168.robot.OI;
import org.team2168.robot.RobotMap;
import org.team2168.robot.PIDController.Controller.PIDSpeed;
import org.team2168.robot.PIDController.Sensors.AverageEncoder;
import org.team2168.robot.PIDController.TCPStream.TCPsocketSender;
import org.team2168.robot.commands.subSystems.ShooterWheel.DriveShooterWithJoystick;

public class ShooterWheel extends Subsystem {
	Talon shooterTalonAft;
	Talon shooterTalonFwd;
	Victor shooterVictorAft;
	Victor shooterVictorFwd;
	
	AverageEncoder shooterWheelEncoderAft;
	AverageEncoder shooterWheelEncoderFwd;
	
	public PIDSpeed shooterWheelSpeedControllerAft;
	public PIDSpeed shooterWheelSpeedControllerFwd;
	
	TCPsocketSender TCPshooterSpeedControllerAft;
	TCPsocketSender TCPshooterSpeedControllerFwd;

	
	public ShooterWheel() {
		if(RobotMap.USE_TALONS_SHOOTER) {
			shooterTalonAft = new Talon(RobotMap.shooterMotorAft);
			shooterTalonFwd = new Talon(RobotMap.shooterMotorFwd);
		} else {
			shooterVictorAft = new Victor(RobotMap.shooterMotorAft);
			shooterVictorFwd = new Victor(RobotMap.shooterMotorFwd);
		}
		//Set Encoder Parameters
		shooterWheelEncoderAft = new AverageEncoder(RobotMap.shooterEncoderAftChannelA, RobotMap.shooterEncoderAftChannelB, RobotMap.shooterEncoderPulsePerRot,RobotMap.shooterEncoderDistPerTick,RobotMap.shooterEncoderReverse, RobotMap.shooterEncodingType, RobotMap.shooterSpeedReturnType, RobotMap.shooterPosReturnType,RobotMap.shooterAvgEncoderVal);
		shooterWheelEncoderAft.setMinRate(RobotMap.shooterEncoderMinRate);
		shooterWheelEncoderAft.setMaxPeriod(RobotMap.shooterEncoderMinPeriod);
		//shooterWheelEncoderAft.setSamplesToAverage((byte) 127);
		shooterWheelEncoderAft.start();

		//Set Encoder Parameters
		shooterWheelEncoderFwd = new AverageEncoder(RobotMap.shooterEncoderFwdChannelA, RobotMap.shooterEncoderFwdChannelB, RobotMap.shooterEncoderPulsePerRot,RobotMap.shooterEncoderDistPerTick,RobotMap.shooterEncoderReverse, RobotMap.shooterEncodingType, RobotMap.shooterSpeedReturnType, RobotMap.shooterPosReturnType,RobotMap.shooterAvgEncoderVal);
		shooterWheelEncoderFwd.setMinRate(RobotMap.shooterEncoderMinRate);
		shooterWheelEncoderFwd.setMaxPeriod(RobotMap.shooterEncoderMinPeriod);
		//shooterWheelEncoderFwd.setSamplesToAverage((byte) 127);
		shooterWheelEncoderFwd.start();
		
		//set controllers		
		shooterWheelSpeedControllerAft = new PIDSpeed("ShooterSpeedControllerAft", RobotMap.shooterSpeedP, RobotMap.shooterSpeedI, RobotMap.shooterSpeedD, shooterWheelEncoderAft, RobotMap.shooterPIDPeriod);
		shooterWheelSpeedControllerAft.setSIZE(RobotMap.shooterPIDArraySize);
		shooterWheelSpeedControllerAft.setAcceptErrorDiff(RobotMap.shooterAcceptError);
		shooterWheelSpeedControllerAft.startThread();

		//set controllers		
		shooterWheelSpeedControllerFwd = new PIDSpeed("ShooterSpeedControllerFwd", RobotMap.shooterSpeedP, RobotMap.shooterSpeedI, RobotMap.shooterSpeedD, shooterWheelEncoderFwd, RobotMap.shooterPIDPeriod);
		shooterWheelSpeedControllerFwd.setSIZE(RobotMap.shooterPIDArraySize);
		shooterWheelSpeedControllerFwd.setAcceptErrorDiff(RobotMap.shooterAcceptError);
		shooterWheelSpeedControllerFwd.startThread();		
		
		//initialized TCP Server, ONLY FOR DEBUDDING, REMOVE FOR COMPETITION
		TCPshooterSpeedControllerAft = new TCPsocketSender(RobotMap.TCPServerShooterSpeedAft, shooterWheelSpeedControllerAft);
		TCPshooterSpeedControllerAft.start();

		//initialized TCP Server, ONLY FOR DEBUDDING, REMOVE FOR COMPETITION
		TCPshooterSpeedControllerFwd = new TCPsocketSender(RobotMap.TCPServerShooterSpeedFwd, shooterWheelSpeedControllerFwd);
		TCPshooterSpeedControllerFwd.start();
		
	}
	
	/**
	 * Set the default command for this subsystem.
	 */
    
	public void initDefaultCommand() {
    	//Set the shooter wheels to not spin.
    	setDefaultCommand(new DriveShooterWithJoystick());
    }
   

    public void driveShooterWheels(double aftWheelSpeed, double fwdWheelSpeed) {    	
    	driveFwdWheel(fwdWheelSpeed);
    	driveAftWheel(aftWheelSpeed);
    	System.out.println(shooterWheelEncoderAft.getRate() + "\t" + shooterWheelSpeedControllerFwd.getSensorRate());
    }
    
    /**
     * drive the right side
     * @param fwdWheelSpeed between -1 and 1
     */
    public void driveFwdWheel(double fwdWheelSpeed) {
    	//RobotMap defines which motors are inverted.
    	if(OI.sFwdInvert)
    		fwdWheelSpeed = -fwdWheelSpeed;

    	fwdWheelSpeed = OI.minJoystickThreshold(fwdWheelSpeed);
    	if(RobotMap.USE_TALONS_SHOOTER) {
    		shooterTalonFwd.set(fwdWheelSpeed);
    	} else {
    		shooterVictorFwd.set(fwdWheelSpeed);
    	}
    }
    
    /**
     * drive the left side
     * @param aftWheelSpeed between -1 and 1
     */
    public void driveAftWheel(double aftWheelSpeed) {
    	//RobotMap defines which motors are inverted.
    	if(OI.sAftInvert)
    		aftWheelSpeed = -aftWheelSpeed;
    	
    	aftWheelSpeed = OI.minJoystickThreshold(aftWheelSpeed);
    	
    	if(RobotMap.USE_TALONS_SHOOTER) {
    		shooterTalonAft.set(aftWheelSpeed);
    	} else {
    		shooterVictorAft.set(aftWheelSpeed);
    	}
    }
}

