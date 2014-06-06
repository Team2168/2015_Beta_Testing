package com.first.team2168.robot.subsystems;



import com.first.team2168.robot.sensors.TCPCameraSensor;

import edu.wpi.first.wpilibj.command.Subsystem;


public class Vision extends Subsystem{
	
	TCPCameraSensor cam;

	private volatile String[] dataReceived;
	private static Vision instance = null;
	
	private int LeftOrRightHot;

	
	
	/**
	 * Default constructor for vision target. TCP Listens on Port 1111;
	 */
	private Vision()
	{
		cam = new TCPCameraSensor(1111, 200);

		//initialize data variable
		dataReceived = new String[cam.getMessageLength()];
		
		for(int i = 0; i < cam.getMessageLength(); i++)
			dataReceived[i] = "0";
		
		LeftOrRightHot = 0;

		cam.start();
	}
	/**
	 * @return singleton
	 */
	public static Vision getInstance() {
		if (instance == null) {
			instance = new Vision();
		}
		return instance;
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}


	/**
	 * @return true when the beaglebone reports the match has started,
	 *   false otherwise
	 */
	public boolean isMatchStart()
	{
		return cam.isMatchStart();	
	}
	
	/**
	 * 
	 * @return true if the beaglebone detected a left hot target
	 *   after match start
	 */
	public boolean isLeftHot()
	{
		if (cam.isMatchStart() && (cam.LeftOrRightHot() == -1))
			return true;
		else 
			return false;
		
	}
	
	/**
	 * 
	 * @return true if the beaglebone detected a right hot target after match
	 *   start.
	 */
	public boolean isRightHot()
	{
		if (cam.isMatchStart() && (cam.LeftOrRightHot() == 1))
			return true;
		else 
			return false;
				
	}
	
	/**
	 * 
	 * @return true if the beaglebone sees a hot target in its current view.
	 *   This ignores match start.
	 */
	public boolean isHotinView()
	{
		return cam.isHotInView();
				
	}
	
	public boolean isValidFrame()
	{
		return cam.isValidFrame();
				
	}
	
	public boolean isCameraConnected()
	{
		return cam.isCameraConnected();
				
	}
	
	public boolean isProcessingTreadRunning()
	{
		return cam.isProcessingTreadRunning();
				
	}
	
	public boolean isBoneConnected()
	{
		return cam.isClientConnected();

	}
/**
 * 
 * @param LorR make 1 for Right, -1 for left, 0 otherwise
 */
	public void setLeftOrRightHot(int LorR)
	{
		LeftOrRightHot = LorR;
	}
	
	public int getLeftOrRightHot()
	{
		return LeftOrRightHot;
	}
	
	public int getCamLeftOrRightHot()
	{
		return cam.LeftOrRightHot();
	}
	
	public boolean getCamLeftHot() {
		return getCamLeftOrRightHot() == -1;
	}
	
	public boolean getCamRightHot() {
		return getCamLeftOrRightHot() == 1;
	}
	
	public double getDistance()
	{
		return cam.getDitance();
	}
}