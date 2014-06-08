package org.team2168.robot;

import edu.wpi.first.wpilibj.CounterBase;
import org.team2168.robot.PIDController.Sensors.AverageEncoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public final static boolean USE_TALONS_DRIVETRAIN = false;
	public final static boolean USE_TALONS_SHOOTER = true;
	
	/****************************************************************
	 *                       CRIO MAP                               *
	 ****************************************************************/	
	//PWM Channels////////////////////////////////////////////////////
	public static final int leftDriveMotor  = 3;
	public static final int rightDriveMotor = 4;
	public static final int shooterMotorAft = 2;
	public static final int shooterMotorFwd = 1;
	public static final int intakeMotorR	= 5;
	public static final int intakeMotorL	= 6;
	

	//DIO Channels////////////////////////////////////////////////////
	public static final int leftDriveEncoderChannelA  = 1;
	public static final int leftDriveEncoderChannelB  = 2;
	public static final int rightDriveEncoderChannelA = 3;
	public static final int rightDriveEncoderChannelB = 4;
	public static final int shooterEncoderFwdChannelA = 5;
	public static final int shooterEncoderFwdChannelB = 6;
	public static final int shooterEncoderAftChannelA = 7;
	public static final int shooterEncoderAftChannelB = 8;
	public static final int intakeLimitSensorR        = 9;
	public static final int intakeLimitSensorL        = 10;
	
	public static final int compressorPressureSwitch = 10;

	//Relay Output Channels///////////////////////////////////////////
//	public static final int compressorPower = 1;
//	public static final int teamDiscLight   = 2;
//	public static final int intake          = 3;
//	public static final int arduinoRelay1   = 5;
//	public static final int arduinoRelay2   = 6;
//	public static final int arduinoRelay3   = 7;
//	public static final int arduinoRelay4   = 8;
	
	//Solenoid Channels(third slot on cRio)///////////////////////////////////////////////
	public static final int shooterRaise               = 8;
	public static final int shooterLower               = 7;
	public static final int hangerEngage               = 3;
	public static final int hangerDisengage            = 4;
	public static final int hopperFire                 = 5;
	public static final int hopperReload               = 6;
	public static final int lightSaberRaise            = 1;
	public static final int lightSaberLower            = 2;
	
	//Solenoid Channels(fourth slot on cRio)/////////////////////////////////////////////////
//	public static final int intakeHopperExtend      = 1;
//	public static final int intakeHopperRetract     = 2;
//	public static final int intakeFloorloadExtend   = 3;
//	public static final int intakeFloorloadRetract  = 4;

	//Analog Input Channels///////////////////////////////////////////
//	public static final int accelerometer = 1;
	public static final int gyroChannel   = 2;

	/****************************************************************
	 *                       Shooter Parameters                     *
	 ****************************************************************/
	
	public static final double AFT_SHOOTERWHEEL_PRE_SPIN_SPEED          = 5000;
	public static final double AFT_SHOOTERWHEEL_WALL_3PT_SPEED          = 5000; 
	public static final double AFT_SHOOTERWHEEL_PYRAMID_5PT_SPEED       = 5000; 
	public static final double AFT_SHOOTERWHEEL_FRONT_PYRAMID_3PT_SPEED = 5000; 
	public static final double AFT_SHOOTERWHEEL_BACK_PYRAMID_3PT_SPEED  = 5000; 
	public static final double AFT_SHOOTERWHEEL_HALF_COURT_3PT_SPEED    = 5000; 
	
	public static final double FWD_SHOOTERWHEEL_PRE_SPIN_SPEED          = 5000;
	public static final double FWD_SHOOTERWHEEL_WALL_3PT_SPEED          = 5000; 
	public static final double FWD_SHOOTERWHEEL_PYRAMID_5PT_SPEED       = 5000; 
	public static final double FWD_SHOOTERWHEEL_FRONT_PYRAMID_3PT_SPEED = 5000; 
	public static final double FWD_SHOOTERWHEEL_BACK_PYRAMID_3PT_SPEED  = 5000; 
	public static final double FWD_SHOOTERWHEEL_HALF_COURT_3PT_SPEED    = 5000; 
	
	//Shooter Wheel Encoder///////////////////////////////////////////////
	//arm Radius
	public static final int shooterWheelRadius = 3;//Colson wheel radius in inches

	private static final int shooterPulsePerRotation = 256; //encoder ticks per rotation (LEAVE PRIVATE)
	private static final double shooterGearRatio = 12.0/60.0; //ratio between wheel over encoder (LEAVE PRIVATE)
	public static final int shooterEncoderPulsePerRot = (int) (shooterPulsePerRotation*shooterGearRatio); //pulse per rotation * gear ratio
	public static final double shooterEncoderDistPerTick = (Math.PI*2*shooterWheelRadius)/shooterEncoderPulsePerRot;
	public static final CounterBase.EncodingType shooterEncodingType = CounterBase.EncodingType.k1X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType shooterPosReturnType = AverageEncoder.PositionReturnType.DEGREE;
	public static final AverageEncoder.SpeedReturnType shooterSpeedReturnType = AverageEncoder.SpeedReturnType.FPS;
	public static final int shooterEncoderMinRate = 10; 
	public static final int shooterEncoderMinPeriod = 10;
	public static final boolean shooterEncoderReverse = true;
	public static final int shooterAvgEncoderVal = 5;
	public static final int shooterAcceptError = 10; //degrees;

	//PID Parameters //////////////////////////////////////////////////
	public static final int shooterPIDArraySize = 50;

	//period to run PID loops on shooter
	public static final long shooterPIDPeriod = 100;//100ms loop

	//PID Gains for Shooter Wheel Speed Controller
	public static final double shooterSpeedP = 0.0002;
	public static final double shooterSpeedI = 0.00001412646174233; 
	public static final double shooterSpeedD = 0.0074778888124088;


	/****************************************************************
	 *                       Arm Parameters                         *
	 ****************************************************************/
	public static final double WALL_3PT_ANGLE          = 102; //up against the goal, NOT tested
	public static final double PYRAMID_5PT_ANGLE       = 102; //NOT tested
	public static final double FRONT_PYRAMID_3PT_ANGLE =  88; //closer to the goal, tested
	public static final double BACK_PYRAMID_3PT_ANGLE  =  76; //further from the goal, tested
	public static final double IN_PYRAMID_3PT_ANGLE    =  75; //further fro
	public static final double HALF_COURT_3PT_ANGLE    =  70; //NOT tested
	public static final double ARM_HORIZONTAL_ANGLE    =  52;
	
	//arm voltage constant
	public static final double hopperVoltage       = 0.65;
	public static final double armConstVoltage     = 0.7; //voltage to overcome 
	public static final double armConstVoltageHome = -0.3; //voltage to zero arm on startup

	//arm Encoder///////////////////////////////////////////////
	//arm Radius
	public static final int armWheelRadius = 3;//Colson wheel radius in inches

	//Arm Encoder Parameters /////////////////////////////////
	private static final int armPulsePerRotation = 256; //encoder ticks per rotation (LEAVE PRIVATE)
	private static final double armGearRatio = 1.024; //ratio between wheel over encoder (LEAVE PRIVATE)
	public static final int armEncoderPulsePerRot = (int) (armPulsePerRotation*armGearRatio); //pulse per rotation * gear ratio
	public static final double armEncoderDistPerTick = (Math.PI*2*armWheelRadius)/armEncoderPulsePerRot;
	public static final CounterBase.EncodingType armEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType armPosReturnType = AverageEncoder.PositionReturnType.DEGREE;
	public static final AverageEncoder.SpeedReturnType armSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int armEncoderMinRate = 10;
	public static final int armEncoderMinPeriod = 10;
	public static final boolean armEncoderReverse = true;
	public static final int armAvgEncoderVal = 2;
	public static final int armAcceptError = 2; //degrees;

	//PID Parameters //////////////////////////////////////////////////
	public static final int armPIDArraySize = 50;

	//period to run PID loops on arm
	public static final long armPIDPeriod = 100;//100ms loop

	//PID Gains for Arm Position Controller

	public static final double armPosP = 0.02;
	public static final double armPosI = 0.0035912646174233;
	public static final double armPosD = 0;
	
	public static final double armPosPDown = 0.02;
	public static final double armPosIDown = 0.00000912646174233; 
	public static final double armPosDDown = 0;
	

	/****************************************************************
	 *                       Drivetrain Parameters                  *
	 ****************************************************************/
	//Wheel Radius
	public static final int driveWheelRadius = 2;//Colson wheel radius in inches

	//Drivetrain Encoder Parameters /////////////////////////////////
	private static final int drivePulsePerRotation = 360; //encoder ticks per rotation
	private static final double driveGearRatio = 30.0/24.0; //ratio between wheel over encoder
	public static final int driveEncoderPulsePerRot = (int) (drivePulsePerRotation*driveGearRatio); //pulse per rotation * gear ratio
	public static final double driveEncoderDistPerTick = (Math.PI*2*driveWheelRadius)/driveEncoderPulsePerRot;
	public static final CounterBase.EncodingType driveEncodingType = CounterBase.EncodingType.k4X; //count rising and falling edges on both channels
	public static final AverageEncoder.PositionReturnType drivePosReturnType = AverageEncoder.PositionReturnType.DEGREE;
	public static final AverageEncoder.SpeedReturnType driveSpeedReturnType = AverageEncoder.SpeedReturnType.RPM;
	public static final int driveEncoderMinRate = 10; 
	public static final int driveEncoderMinPeriod = 10;
	public static final boolean leftDriveTrainEncoderReverse = true;
	public static final boolean rightDriveTrainEncoderReverse = false;
	public static final int driveAvgEncoderVal = 5;

	//PID Parameters //////////////////////////////////////////////////
	public static final int drivetrainPIDArraySize = 50;

	//period to run PID loops on drive train
	public static final long driveTrainPIDPeriod = 100;//100ms loop

	//PID Gains for Left & Right Speed and Position
	//Bandwidth =
	//Phase Margin = 
	public static final double driveTrainLeftSpeedP =  0.002;
	public static final double driveTrainLeftSpeedI =  0.0001412646174233; 
	public static final double driveTrainLeftSpeedD =  0.0074778888124088;

	public static final double driveTrainRightSpeedP = 0.002;
	public static final double driveTrainRightSpeedI = 0.0001412646174233;  
	public static final double driveTrainRightSpeedD = 0.0074778888124088;

	public static final double driveTrainLeftPositionP = 0.002;
	public static final double driveTrainLeftPositionI = 0.0001412646174233;  
	public static final double driveTrainLeftPositionD = 0.0074778888124088;

	public static final double driveTrainRightPositionP = 0.002;
	public static final double driveTrainRightPositionI = 0.0001412646174233; 
	public static final double driveTrainRightPositionD = 0.0074778888124088;
	
	//autonomous gyro angle error.
	public static final double angleValRange = 1.5;


	/****************************************************************
	 *                TCP Servers  (ONLY FOR DEBUGGING)             *
	 ****************************************************************/
	public static final int TCPServerRightDrivetrainPos = 1180;
	public static final int TCPServerRightDrivetrainSpeed = 1181;
	public static final int TCPServerLeftDrivetrainPos = 1182;
	public static final int TCPServerLeftDrivetrainSpeed = 1183;
	public static final int TCPServerShooterSpeedAft = 1184;
	public static final int TCPServerShooterSpeedFwd = 1185;
	public static final int TCPServerArmPos = 1186;

}
