package frc2168_2013.utils;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.visa.Visa;
import edu.wpi.first.wpilibj.visa.VisaException;

/**
 * This singleton class handles communications with a arduino over RS-232.
 *  
 * @author James
 *
 */
public class SerialCommunicator {

	private static SerialCommunicator instance = null;
	private static SerialPort serial;

	private static int m_resourceManagerHandle;
    private static int m_portHandle;
	
	/**
	 * Singleton class. Only one instance can exist in the world.
	 */
	SerialCommunicator(){
	}
	
	/**
	 * Initialize the serial communicator (create serial port).
	 * 
	 * @param baudRate
	 * @param dataBits
	 * @param parity
	 * @param stopBits
	 */
	public static void init(int baudRate, int dataBits, SerialPort.Parity parity, SerialPort.StopBits stopBits) {
		if(serial == null) { //only initialize if we haven't already done so.
			try {
				serial = new SerialPort(baudRate, dataBits, parity, stopBits);
				
				//No delimiter between messages, read returns whatever data is in the buffer.
				//serial.disableTermination();
				//Flush the buffer each time data is sent.
				//serial.setWriteBufferMode(SerialPort.WriteBufferMode.kFlushOnAccess);
				
				m_resourceManagerHandle = Visa.viOpenDefaultRM();
		        m_portHandle = Visa.viOpen(m_resourceManagerHandle, "ASRL1::INSTR", 0, 0);
			} catch (VisaException e) {
				System.out.println("Could not open or configure the serial port.");
				e.printStackTrace();
				serial = null;
			}
		}
	}
	
	/**
	 * Closes the serial port
	 */
	public static void free() {
		if(serial != null) {
			serial.free();
			serial = null;
		}
		m_resourceManagerHandle = 0;
		m_portHandle = 0;
	}

	/**
	 * 
	 * 
	 * @return the serial communicator instance
	 */
	public static SerialCommunicator getInstance() {
		if(instance == null) {
			instance = new SerialCommunicator();
		}

		return instance;
	}
	
	/**
	 * Determine if any data has been received.
	 * 
	 * @return true if data is ready to be read, false otherwise
	 */
	public static boolean isDataReady() {
		boolean retVal = false;
		
		if (serial != null) {
			try {
				retVal = (serial.getBytesReceived() > 0);
			} catch (VisaException e) {
				free();
				e.printStackTrace();
			}
		}
		
		return retVal;
	}
	
	/**
	 * Read data from the serial port.
	 * 
	 * @bug http://firstforge.wpi.edu/sf/sfmain/do/go/artf1596
	 * @return the data read from the serial port, or an empty string if there was an error.
	 */
	public static String getData() {
		String retVal = "";
		/** The SerialPort class isn't flushing the read buffer. This function is useless until that's fixed.
		 * Bug written: http://firstforge.wpi.edu/sf/sfmain/do/go/artf1596
		if (serial != null) {
			try {
				retVal = serial.readString();
				Visa.viFlush(m_portHandle, 4);
			} catch (VisaException e) {
				free();
				e.printStackTrace();
			}
		}
		*/
		return retVal;
	}
	
	/**
	 * Write data to the serial port.
	 * 
	 * @param data The data to be written.
	 */
	public static void putData(String data) {
		if (serial != null) {
			try {
				serial.write(data.getBytes(), data.length());
			} catch (VisaException e) {
				free();
				e.printStackTrace();
			}
		}
	}
}
