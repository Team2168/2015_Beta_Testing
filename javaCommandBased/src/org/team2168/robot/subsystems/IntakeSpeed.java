package frc2168_2013.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc2168_2013.OI;
import frc2168_2013.RobotMap;
import frc2168_2013.commands.subSystems.Intake.DriveIntakeConstant;

public class IntakeSpeed extends Subsystem {		

	Talon motorR, motorL;
	DigitalInput limitSensorR, limitSensorL;

	double leftHopper = 0.0;
	double rightHopper = 0.0;

	public IntakeSpeed() {
		motorR = new Talon(RobotMap.intakeMotorR);
		motorL = new Talon(RobotMap.intakeMotorL);
		limitSensorR = new DigitalInput(RobotMap.intakeLimitSensorR);
		limitSensorL = new DigitalInput(RobotMap.intakeLimitSensorL);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveIntakeConstant(0.0, 0.0));
	}

	/**
	 * Check if a disc is present on the right side of the intake.
	 * @return true if present
	 */
	public boolean rightFull(){
		return !limitSensorR.get();
	}

	/**
	 * Check if a disc is present on the left side of the intake.
	 * 
	 * @return true if present
	 */
	public boolean leftFull(){
		return !limitSensorL.get();
	}

	/**
	 * Drive both sides, will stop each respective motor if a disc is present
	 * 
	 * @param left speed
	 * @param right speed
	 */
	public void driveIntakeTillFull(double left, double right){
		driveRightTillFull(right);
		driveLeftTillFull(left);
	}

	/**
	 * Drive the left side of the intake unless left switch is pressed
	 * 
	 * @param left side speed
	 */
	public void driveLeftTillFull(double left) {
		if(leftFull()){
			driveLeft(0.0);
		} else {
			driveLeft(left);
		}
	}

	/**
	 * Drive the right side of the intake unless right switch is pressed
	 * @param right side speed
	 */
	public void driveRightTillFull(double right) {
		if(rightFull()){
			driveRight(0.0);
		} else {
			driveRight(right);
		}
	}

	/**
	 * drive both sides of intake at constant speed, written to unload intake into hopper
	 * 
	 * @param leftHopper speed
	 * @param rightHopper speed
	 */
	public void driveIntake(double leftHopper, double rightHopper){
		driveRight(rightHopper);
		driveLeft(leftHopper);
	}

	/**
	 * drive the left side of the intake
	 * @param leftHopper speed
	 */
	public void driveLeft(double leftHopper) {
		if (OI.lIntakeInvert) {
			motorL.set(-leftHopper);
		} else {
			motorL.set(leftHopper);
		}
	}


	/**
	 * Drive the right side of the intake
	 * 
	 * @param rightHopper speed
	 */
	public void driveRight(double rightHopper) {
		if (OI.rIntakeInvert) {
			motorR.set(-rightHopper);
		} else {
			motorR.set(rightHopper);
		}
	}

	/**
	 * Determine the number of discs in the intake
	 * 
	 * @return the number of discs in the intake
	 */
	public int getNumberOfDiscs() {
		int discs = 0;

		if (rightFull()) {
			discs++;
		}
		if (leftFull()) {
			discs++;
		}

		return discs;
	}

	/**
	 * Check if the intake if full
	 * @return true if the intake has two discs presesnt
	 */
	public boolean intakeFull() {
		return rightFull() && leftFull();
	}
}
