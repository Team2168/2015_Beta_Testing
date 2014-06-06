#include "WPILib.h"
#include <iostream>
#include "Can/PDP.h"
#include "Can/PCM.h"

class Robot: public IterativeRobot
{
private:
	LiveWindow *lw;
	PDP *canPDP;
	PCM *canPCM;

	Talon *leftDrive;
	Talon *rightDrive;

	Joystick *driverStick;


	void RobotInit()
	{
		lw = LiveWindow::GetInstance();

		canPDP =  new PDP();
		canPCM = new PCM();
		leftDrive = new Talon(1);
		rightDrive = new Talon(2);

		driverStick = new Joystick(1);

		std::cout<<"Robot Program Running";
	}

	void AutonomousInit()
	{

	}

	void AutonomousPeriodic()
	{

	}

	void TeleopInit()
	{

//leftDrive->SetSpeed(0.0);
//rightDrive->SetSpeed(0.0);

	}

	void TeleopPeriodic()
	{

		unsigned int rightStick = 2;
		unsigned int leftStick = 5;

		leftDrive->SetSpeed(1.0);


		double current;
		double voltage;
		double temp;



		if(canPDP == 0)
			std::cout<<"NULL"<<std::endl;
		else
		{
			canPDP->GetChannelCurrent(3,current);
			canPDP->GetVoltage(voltage);
			canPDP->GetTemperature(temp);
		}

		std::cout<<"current: "<<current<<"\t voltage: "<<voltage<<"\t temp: "<<temp<<std::endl;


	}

	void DisabledInit()
	{

	}

	void DisabledPeriodic()
	{



	}

	void TestInit()
	{

	}

	void TestPeriodic()
	{
		lw->Run();
	}
};

START_ROBOT_CLASS(Robot);
