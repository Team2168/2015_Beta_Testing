package org.team2168.robot.commands.subSystems.ShooterWheel;

import org.team2168.robot.Robot;
import org.team2168.robot.commands.CommandBase;

public class PID_ShooterAtSpeed extends CommandBase
{
	private int count = 0;
	private volatile double[] atSpeed;
	private int sp;
	
	public PID_ShooterAtSpeed() {
		//no need to require the subsystem
		//the PID controller owns the subsystem
		
		this.atSpeed = new double[10]; //timing should be size * 20ms
		
		this.sp = 110;
		
	}


	protected void initialize() {
		// TODO Auto-generated method stub


	}


	protected void execute() {
		// TODO Auto-generated method stub

	}


	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
			//finish is based on verifying the voltage is constant over some length of time
			if (count==this.atSpeed.length)
				count=0;
			
			atSpeed[count]=shooterWheel.shooterWheelSpeedControllerFwd.getSensorRate();
			count++;
			
			
			for(int j=1; j<atSpeed.length; j++)
			{
				if ((atSpeed[j]>= this.sp-10) )
					continue; //great check next value;
				else
				{
					//came across a value in the array that was not in range
					CommandBaseRobot.setShooterAtSpeed(false);
					return false; //terminate because we know the array is not filled
				}
			}
			
			//if we get here then the array was filled with atSpeed.lenght-1 values that were
			//in range so set the atSpeed boolean and return true.
			CommandBaseRobot.setShooterAtSpeed(true);
			return true;

	}


	protected void end() {
		// TODO Auto-generated method stub
		

	}


	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}