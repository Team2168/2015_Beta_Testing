package org.team2168.robot.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.team2168.robot.RobotMap;

public class IntakePosition extends Subsystem {	

	DoubleSolenoid actuatorHopper, actuatorFloorload;
	Talon intakeMotorR, intakeMotorL;
//	ADXL345_I2C accelerometer;
//	
//	double xAccel = accelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
//	double yAccel = accelerometer.getAcceleration(ADXL345_I2C.Axes.kY);
//	double zAccel = accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ);
	
	
	public IntakePosition() {
		actuatorHopper = new DoubleSolenoid(2, RobotMap.intakeHopperExtend,
				RobotMap.intakeHopperRetract);
		actuatorFloorload = new DoubleSolenoid(2, RobotMap.intakeFloorloadExtend,
				RobotMap.intakeFloorloadRetract);
		
//		accelerometer = new ADXL345_I2C(RobotMap.accelerometer, ADXL345_I2C.DataFormat_Range.k2G);
	}

	public void initDefaultCommand() {

	}

	/**
	 * Lower intake to load position
	 */
	public void load(){
		actuatorHopper.set(DoubleSolenoid.Value.kReverse);
		actuatorFloorload.set(DoubleSolenoid.Value.kReverse);
	}


	/**
	 * Raise the intake mechanism to hopper position
	 */
	public void hopper() {
		actuatorHopper.set(DoubleSolenoid.Value.kReverse);
		actuatorFloorload.set(DoubleSolenoid.Value.kForward);
	}

	/**
	 * Raise the intake mechanism to stow position
	 */
	public void stow(){
		actuatorHopper.set(DoubleSolenoid.Value.kForward);
		actuatorFloorload.set(DoubleSolenoid.Value.kForward);
	}
	
//	public void printAccReadings(){
//		System.out.println("X = " + xAccel + "     Y = " + yAccel + "     Z = " + zAccel);
//		//System.out.println("X = " + Math.asin(xAccel) + "     Y = " + Math.asin(yAccel) + "     Z = " + Math.asin(zAccel));
//		
//	}
	
//	public boolean intakeUp(){
//		
//		if(){
//			return true;
//		} else {
//			return false;
//		}		
//	}
	
//public boolean intakeDown(){
//		
//		if(){
//			return true;
//		} else {
//			return false;
//		}		
//	}
}
