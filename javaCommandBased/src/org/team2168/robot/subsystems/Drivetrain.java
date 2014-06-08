package org.team2168.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2168.robot.OI;
import org.team2168.robot.RobotMap;
import org.team2168.robot.PIDController.Controller.PIDPosition;
import org.team2168.robot.PIDController.Controller.PIDSpeed;
import org.team2168.robot.PIDController.Sensors.AverageEncoder;
import org.team2168.robot.PIDController.TCPStream.TCPsocketSender;
import org.team2168.robot.commands.subSystems.DriveTrain.DriveWithJoystick;

public class Drivetrain extends Subsystem {
	
	double leftSpeed = 0;
	double rightSpeed = 0;
	
	//declare sensors
	AverageEncoder rightEncoder;
	AverageEncoder leftEncoder;
	
	//declare position controllers
	public PIDPosition rightPosController;
	public PIDPosition leftPosController;
	
	//declare speed controllers
	public PIDSpeed rightSpeedController;
	public PIDSpeed leftSpeedController;
	
	//declare TCP severs...ONLY FOR DEBUGGING PURPOSES, SHOULD BE REMOVED FOR COMPITITION
	TCPsocketSender TCPrightPosController;
	TCPsocketSender TCPrightSpeedController;
	TCPsocketSender TCPleftPosController;
	TCPsocketSender TCPleftSpeedController;

	Talon rightTalonDriveMotor;
	Talon leftTalonDriveMotor;

	Victor rightVictorDriveMotor;
	Victor leftVictorDriveMotor;
	
	Gyro turnSense;
	
	/**
	 * The default constructor for the Drivetrain subsystem.
	 */
    public Drivetrain(){
    	//System.out.println("drive train encoder stuff:" + RobotMap.driveEncoderPulsePerRot);
    
    	//declare drivetrain motor controllers
    	//intializing motor controller using PWM. Refer to RobotMap
    	if(RobotMap.USE_TALONS_DRIVETRAIN) {
        	rightTalonDriveMotor = new Talon (RobotMap.rightDriveMotor);
        	leftTalonDriveMotor = new Talon (RobotMap.leftDriveMotor);	
    	} else {
    		rightVictorDriveMotor = new Victor (RobotMap.rightDriveMotor);
        	leftVictorDriveMotor = new Victor (RobotMap.leftDriveMotor);    		
    	}
    	
    	//initialized right and left drive train encoders
    	rightEncoder = new AverageEncoder(RobotMap.rightDriveEncoderChannelA,
    			RobotMap.rightDriveEncoderChannelB, RobotMap.driveEncoderPulsePerRot,
    			RobotMap.driveEncoderDistPerTick, RobotMap.rightDriveTrainEncoderReverse,
    			RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType,
    			RobotMap.drivePosReturnType, RobotMap.driveAvgEncoderVal);
    	rightEncoder.setPosReturnType(AverageEncoder.PositionReturnType.INCH);
    	rightEncoder.setMaxPeriod(RobotMap.driveEncoderMinPeriod);//min period before reported stopped
    	rightEncoder.setMinRate(RobotMap.driveEncoderMinRate);//min rate before reported stopped
    	rightEncoder.start();
    	
    	leftEncoder = new AverageEncoder(RobotMap.leftDriveEncoderChannelA,
    			RobotMap.leftDriveEncoderChannelB, RobotMap.driveEncoderPulsePerRot,
    			RobotMap.driveEncoderDistPerTick, RobotMap.leftDriveTrainEncoderReverse,
    			RobotMap.driveEncodingType, RobotMap.driveSpeedReturnType,
    			RobotMap.drivePosReturnType,RobotMap.driveAvgEncoderVal);
    	leftEncoder.setPosReturnType(AverageEncoder.PositionReturnType.INCH);
    	leftEncoder.setMaxPeriod(RobotMap.driveEncoderMinPeriod);//min period before reported stopped
    	leftEncoder.setMinRate(RobotMap.driveEncoderMinRate);//min rate before reported stopped
    	leftEncoder.start();
    	
    	//Spawn new PID Controller
    	rightSpeedController = new PIDSpeed("RightSpeedController", RobotMap.driveTrainRightSpeedP,
    			RobotMap.driveTrainRightSpeedI, RobotMap.driveTrainRightSpeedD, rightEncoder,
    			RobotMap.driveTrainPIDPeriod);
    	rightPosController = new PIDPosition("RightPositionController", RobotMap.driveTrainRightPositionP,
    			RobotMap.driveTrainRightPositionI, RobotMap.driveTrainRightPositionD, rightEncoder,
    			RobotMap.driveTrainPIDPeriod);
    	leftSpeedController = new PIDSpeed("LeftSpeedController", RobotMap.driveTrainLeftSpeedP,
    			RobotMap.driveTrainLeftSpeedI, RobotMap.driveTrainLeftSpeedD, leftEncoder,
    			RobotMap.driveTrainPIDPeriod);
    	leftPosController = new PIDPosition("LeftPositionController", RobotMap.driveTrainLeftPositionP,
    			RobotMap.driveTrainLeftPositionI, RobotMap.driveTrainLeftPositionD, leftEncoder,
    			RobotMap.driveTrainPIDPeriod);
    	
    	//add min and max output defaults and set array size
    	rightSpeedController.setSIZE(RobotMap.drivetrainPIDArraySize);
    	leftSpeedController.setSIZE(RobotMap.drivetrainPIDArraySize);
    	rightPosController.setSIZE(RobotMap.drivetrainPIDArraySize);
    	leftPosController.setSIZE(RobotMap.drivetrainPIDArraySize);    	
    	
    	//start controller threads
    	rightSpeedController.startThread();
    	rightPosController.startThread();
    	leftSpeedController.startThread();
    	leftPosController.startThread();
    	
    	//start TCP Servers for DEBUGING ONLY
    	TCPrightPosController = new TCPsocketSender(RobotMap.TCPServerRightDrivetrainPos, rightPosController);
    	TCPrightPosController.start();
    	
    	TCPrightSpeedController = new TCPsocketSender(RobotMap.TCPServerRightDrivetrainSpeed, rightSpeedController);
    	TCPrightSpeedController.start();
    	
    	TCPleftPosController = new TCPsocketSender(RobotMap.TCPServerLeftDrivetrainPos, leftPosController);
    	TCPleftPosController.start();
    	
    	TCPleftSpeedController = new TCPsocketSender(RobotMap.TCPServerLeftDrivetrainSpeed, leftSpeedController);
    	TCPleftSpeedController.start();
    	
    	turnSense = new Gyro(RobotMap.gyroChannel);
    	turnSense.setSensitivity(0.0070);
    	resetAngle();
    	
    	//TODO: initialize encoders and closed loop control of drivetrain
    }
	
    /**
     * Initialize the default command for the drivetrain subsystem.
     */
    
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
    }
	
    /**
     * Drive drive train using tank drive
     * 
     * @param rightSpeed speed for right motors (1 to -1)
     * @param leftSpeed speed for left motors (1 to -1)
     */
    public void tankDrive(double rightSpeed, double leftSpeed) {    	
    	driveRight(rightSpeed);
    	driveLeft(leftSpeed);
    }
    
    /**
     * drive the right side
     * @param rightSpeed between -1 and 1
     */
    public void driveRight(double rightSpeed) {
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(OI.rInvert) {
    		rightSpeed = -rightSpeed;
    	}

    	rightSpeed = OI.minJoystickThreshold(rightSpeed);
    	
    	this.rightSpeed = rightSpeed;
    	
    	if(RobotMap.USE_TALONS_DRIVETRAIN) {
    		rightTalonDriveMotor.set(rightSpeed);
    	} else {
    		rightVictorDriveMotor.set(rightSpeed);
    	}
    }
    
    /**
     * drive the left side
     * @param leftSpeed between -1 and 1
     */
    public void driveLeft(double leftSpeed) {
    	//RobotMap defines which motors are inverted on drivetrain.
    	if(OI.lInvert) {
    		leftSpeed = -leftSpeed;
    	}
    	
    	leftSpeed = OI.minJoystickThreshold(leftSpeed);
    	
    	this.leftSpeed = leftSpeed;
    	
    	if(RobotMap.USE_TALONS_DRIVETRAIN) {
    		leftTalonDriveMotor.set(leftSpeed);
    	} else {
    		leftVictorDriveMotor.set(leftSpeed);
    	}
    }

    
    
    /**
     * Zero the distance traveled by the drivetrain.
     */
    public void resetDistance() {
    	rightEncoder.reset();
    	leftEncoder.reset();
    }
    
    /**
     * Get the accumulated distance traveled since the last reset.
     * 
     * @return distance in inches
     */
    public double getDistance(){
//    	System.out.println("RightEncoder = " + rightEncoder.getPos());
//    	System.out.println("LeftEncoder = " + leftEncoder.getPos());
//    	System.out.println("Gyro = " + getAngle());
    	
    	//TODO: change back to use both encoders
    	//The practice chassis only has the right encoder wired
    	return ((rightEncoder.getPos() + leftEncoder.getPos())/2);
    	//return rightEncoder.getPos();
    }
    
    public double getRightDistance() {
    	return rightEncoder.getPos();
    }
    
    public double getLeftDistance() {
    	return leftEncoder.getPos();
    }
    
    /**
     * Get the current heading of the robot from the gyro sensor.
     * 
     * @return The heading of the robot in degrees
     */
    public double getAngle(){
    	return turnSense.getAngle();	
    }
    
    /**
     * Zeros the drivetrain gyro sensor.
     */
    public void resetAngle(){
    	turnSense.reset();
    }
    
    /**
     * A  simple rate limiter.
     * http://www.chiefdelphi.com/forums/showpost.php?p=1212189&postcount=3
     * 
     * @param input the input value (speed from command/joystick)
	 * @param speed the speed currently being traveled at
	 * @param limit the rate limit
	 * @return the new output speed (rate limited)
     */
    public static double rateLimit(double input, double speed, double limit) {
    	return rateLimit(input, speed, limit, limit);
    }
    
    /**
	 * A simple rate limiter.
	 * http://www.chiefdelphi.com/forums/showpost.php?p=1212189&postcount=3
	 * 
	 * @param input the input value (speed from command/joystick)
	 * @param speed the speed currently being traveled at
	 * @param posRateLimit the rate limit for accelerating
	 * @param negRateLimit the rate limit for decelerating
	 * @return the new output speed (rate limited)
	 */
	public static double rateLimit(double input, double speed,
			double posRateLimit, double negRateLimit) {
		if (input > 0) {
			if (input > (speed + posRateLimit)) {
				//Accelerating positively
		        speed = speed + posRateLimit;
			} else if (input < (speed - negRateLimit)) {
				//Decelerating positively 
		        speed = speed - negRateLimit;
			} else {
		        speed = input;
			}
		} else {
			if (input < (speed - posRateLimit)) {
				//Accelerating negatively
		        speed = speed - posRateLimit;
			} else if (input > (speed + negRateLimit)) {
				//Decelerating negatively
		        speed = speed + negRateLimit;
			} else {
		        speed = input;
			}
		}
		return speed;
	}
	
}

