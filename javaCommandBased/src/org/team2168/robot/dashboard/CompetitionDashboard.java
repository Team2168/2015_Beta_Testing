package org.team2168.robot.dashboard;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.team2168.robot.commands.CommandBase;

public class CompetitionDashboard extends CommandBase
{

public CompetitionDashboard()
{
        //Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(shooterWheel);
        SmartDashboard.putData(shooterAngle);
        SmartDashboard.putData(hopper);
        SmartDashboard.putData(hanger);
        


//add clock
SmartDashboard.putString("compTime", "");


}



protected void initialize()
{
// TODO Auto-generated method stub

}



protected void execute()
{
// TODO Auto-generated method stub
//put Shooter data on screen
SmartDashboard.putNumber("shooterEncoder", shooterWheel.shooterWheelSpeedControllerAft.getSensorRate());
SmartDashboard.putNumber("shooter PID Output", shooterWheel.shooterWheelSpeedControllerAft.getControlOutput());
SmartDashboard.putBoolean("shooterAtSpeed", shooterWheel.shooterWheelSpeedControllerAft.isFinished());
SmartDashboard.putBoolean("enable", shooterWheel.shooterWheelSpeedControllerAft.isEnabled());
SmartDashboard.putNumber("executionTime", shooterWheel.shooterWheelSpeedControllerAft.getExecutionTime());
SmartDashboard.putNumber("shooterSetPoint", shooterWheel.shooterWheelSpeedControllerAft.getSetPoint());
SmartDashboard.putNumber("shooter Err", shooterWheel.shooterWheelSpeedControllerAft.getError());
SmartDashboard.putNumber("shooter acceptErr", shooterWheel.shooterWheelSpeedControllerAft.getAcceptErrorDiff());




}



protected boolean isFinished()
{
// TODO Auto-generated method stub
return false;
}



protected void end()
{
// TODO Auto-generated method stub

}



protected void interrupted()
{
// TODO Auto-generated method stub

}

}