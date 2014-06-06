package com.first.team2168.robot;

import java.util.TimerTask;
import com.first.team2168.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ConsolePrinter {
	// tread executor
	private java.util.Timer executor;
	private long period;

	public ConsolePrinter(long period) {
		this.period = period;

	}

	public void startThread() {
		this.executor = new java.util.Timer();
		this.executor.schedule(new ConsolePrintTask(this), 0L, this.period);
	}
	
	public void print()
	{
	
		SmartDashboard.putBoolean("DIO1:", Robot.DIO1.get() );
		SmartDashboard.putBoolean("DIO2:", Robot.DIO2.get() );
		SmartDashboard.putBoolean("DIO3:", Robot.DIO3.get() );
		SmartDashboard.putBoolean("DIO4:", Robot.DIO4.get() );
		SmartDashboard.putBoolean("DIO5:", Robot.DIO5.get() );
		SmartDashboard.putBoolean("DIO6:", Robot.DIO6.get() );
		SmartDashboard.putBoolean("DIO7:", Robot.DIO7.get() );
		SmartDashboard.putBoolean("DIO8:", Robot.DIO8.get() );
		SmartDashboard.putBoolean("DIO9:", Robot.DIO9.get() );
		
		
		SmartDashboard.putNumber("AI1", Robot.AI1.getValue() );
		SmartDashboard.putNumber("AI2", Robot.AI2.getValue() );
		SmartDashboard.putNumber("AI3", Robot.AI3.getValue() );
		SmartDashboard.putNumber("AI4", Robot.AI4.getValue() );
		
		SmartDashboard.putData("CompressorRelay", Robot.CompressorRelay);

		
		
//    	SmartDashboard.putNumber("Left Encoder Distance",Drivetrain.getInstance().getLeftEncoderDistance());
//    	SmartDashboard.putNumber("Right Encoder Distance:",Drivetrain.getInstance().getRightEncoderDistance());
//    	SmartDashboard.putNumber("GYRO Angle:", Drivetrain.getInstance().getGyroAngle());
//    	SmartDashboard.putNumber("Winch Distance:",Winch.getInstance().getWinchEncoderDistance());
//    	SmartDashboard.putNumber("Winch Speed:",Winch.getInstance().getWinchSpeed());
//		SmartDashboard.putBoolean("Winch Limit:", Winch.getInstance().isCatapultRetracted());
//		SmartDashboard.putBoolean("Intake Limit:", IntakePosition.getInstance().getIntakeLimitSwitch());
//		SmartDashboard.putNumber("Pot Voltage:", Winch.getInstance().getWinchPotentiometerVoltage());	
//		SmartDashboard.putNumber("Catapult Angle", Winch.getInstance().getCatapultAngle());
//		
//		SmartDashboard.putString("AutoName", Robot.getAutoName());
//		
//		
//		SmartDashboard.putNumber("Intake Ball presence", Winch.getInstance().getIntakeBallSensorVoltage());
//		SmartDashboard.putNumber("Winch ball presence", Winch.getInstance().getWinchBallSensorVoltage());
//		
//		
//		SmartDashboard.putBoolean("Camera Status", Vision.getInstance().isCameraConnected());
//		SmartDashboard.putBoolean("Bone Status", Vision.getInstance().isBoneConnected());
//		SmartDashboard.putBoolean("Processing Status", Vision.getInstance().isProcessingTreadRunning());
//		SmartDashboard.putBoolean("HotGoal Status", Vision.getInstance().isHotinView());
//		
//		SmartDashboard.putBoolean("Truss Status", Tusks.getInstance().isTrussShot());
//		SmartDashboard.putBoolean("Wall Status", Tusks.getInstance().isShortRangeShot());
//		SmartDashboard.putBoolean("Far Status", Tusks.getInstance().isLongRangeShot());


	}

	private class ConsolePrintTask extends TimerTask {
		private ConsolePrinter console;

		private ConsolePrintTask(ConsolePrinter printer) {
			if (printer == null) {
				throw new NullPointerException("printer was null");
			}
			this.console = printer;
		}

		/**
		 * Called periodically in its own thread
		 */
		public void run() {
			console.print();
		}
	}
}
