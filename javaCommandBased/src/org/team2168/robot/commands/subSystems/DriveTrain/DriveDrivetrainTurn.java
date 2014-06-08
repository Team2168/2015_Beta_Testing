package frc2168_2013.commands.subSystems.DriveTrain;

import frc2168_2013.OI;
import frc2168_2013.commands.CommandBase;
import frc2168_2013.subsystems.Drivetrain;

public class DriveDrivetrainTurn extends CommandBase {

	private double destinationAngle = 0; //The goal distance in inches
	private double currentAngle = 0;
	private boolean finished;
	
	private double fwdRotationSpeed = 0.0, revRotationSpeed = 0.0, leftToGo = 0.0;
	private static final double ERROR = 0.5; //acceptable positional error in deg.
	private boolean dimeTurning = false;
	
	//Ramp down function
	//TODO: Verify speeds on carpet!
	private static final double SLOW_SPEED = 0.42;				//The slowest speed to drive at
	private static final double TOP_SPEED  = 0.64;				//The top speed to rotate at
	private static final double START_SLOWING_DEGREES = 35.0;	//The angle at which to start slowing down
	private static final double STOP_SLOWING_DEGREES  =  0.0;	//The angle at which to stop slowing down
	private static final double M = (TOP_SPEED - SLOW_SPEED) / (START_SLOWING_DEGREES - STOP_SLOWING_DEGREES);
	private static final double B = TOP_SPEED - (M * START_SLOWING_DEGREES);
	private static final double ACCEL_RATE_LIMIT = 0.05, DECEL_RATE_LIMIT = 0.15;
	
	/**
	 * Turn the drivetrain.
	 * This does not turn on a dime, it will turn in an arc (one side of drivetrain fixed).
	 * 
	 * @param angle The angle to turn, in degrees (positive is clockwise) 
	 */
	public DriveDrivetrainTurn(double angle) {
		this(angle, false);
	}
	
	/**
	 * Turn the drivetrain.
	 * 
	 * @param angle The angle to turn, in degrees (positive is clockwise)
	 * @param dime Enable on a dime turning (rotate in place). If not, non turning side doesn't rotate.
	 * NOTE: Turning on a dime with the 2013 drivetrain doesn't work very well. LOTS OF DRAG
	 * wheel will not rotate (turn in arc).
	 */
	public DriveDrivetrainTurn(double angle, boolean dime) {
		requires(drivetrain);
		destinationAngle = angle;
		dimeTurning = dime;
	}
	
	
	protected void initialize() {
		finished = false;
		
		drivetrain.tankDrive(0.0, 0.0);
		drivetrain.resetAngle();
		
		fwdRotationSpeed = OI.minDriveSpeed;
		revRotationSpeed = -OI.minDriveSpeed;
	}


	/**
	 * Rotate until we are at the desired angle, within some tolerance.
	 * If we overshoot, drive backwards.
	 */
	protected void execute() {
		currentAngle = drivetrain.getAngle();
		leftToGo = Math.abs(currentAngle - destinationAngle);
		
		fwdRotationSpeed = rampDown(leftToGo, fwdRotationSpeed);
		if(dimeTurning) {
			//Turn on a dime
			//TODO: is this right? Shouldn't one be the negative of the other to cause turning?
			revRotationSpeed = fwdRotationSpeed;
		} else {
			//If we aren't turning on a dime, turn the reverse wheel at the
			//  slow speed to keep the turning radius down
			revRotationSpeed = Drivetrain.rateLimit(-SLOW_SPEED, revRotationSpeed,
					ACCEL_RATE_LIMIT, DECEL_RATE_LIMIT);
		}
		System.out.println("Gyro: " + currentAngle + "  left: " + leftToGo +
				"   fwdSpeed: " + fwdRotationSpeed + "  revSpeed: " + revRotationSpeed);
		
		//Positive angle is rotation clockwise, negative is counter-clockwise
		if (leftToGo < ERROR) {
			//We're there
			drivetrain.tankDrive(0, 0);
			finished = true;
		} else if (destinationAngle > currentAngle) {
			//rotate clockwise
			drivetrain.tankDrive(revRotationSpeed, fwdRotationSpeed);
		} else {
			//rotate counterclockwise
			drivetrain.tankDrive(fwdRotationSpeed, revRotationSpeed);
		}
	}

	
	
	protected boolean isFinished() {
		return finished;
	}

	
	
	protected void end() {
		//make sure we are stopped for good measure
		drivetrain.tankDrive(0, 0);
	}

	
	
	protected void interrupted() {
		//Clear the current command to motor controllers if we're interrupted.
		drivetrain.tankDrive(0, 0);
	}
	
	/**
	 * A function to ramp down the turning speed as we approach the destination angle;
	 * @param angleLeft how many degrees left till we get to the destination.
	 * @param currentSpeed the speed the motor is currently being commanded to.
	 * @return the motor drive speed as controlled by the constants in this class.
	 */
	private static double rampDown(double angleLeft, double currentSpeed) {
		double speed;// = (M * angleLeft) + B;
		if(angleLeft < START_SLOWING_DEGREES) {
			//slow down
			speed = SLOW_SPEED;
		} else {
			//speed up
			speed = TOP_SPEED;
		}
		
		speed = Drivetrain.rateLimit(speed, currentSpeed, ACCEL_RATE_LIMIT, DECEL_RATE_LIMIT);
		
		//Cap the top speed
		if(speed > TOP_SPEED) {
			speed = TOP_SPEED;
		}
		
		//make sure min speed isn't slower than slowest speed that actually causes motion
		if(speed < OI.minDriveSpeed && speed > 0 ) {
			speed = OI.minDriveSpeed;
		}
		
		return speed;
	}
}
