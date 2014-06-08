package frc2168_2013.PIDController.TCPStream;

import java.util.Vector;

public class SplitStrings
{

	public static String[] Split(String splitStr, String delimiter) {
	     StringBuffer token = new StringBuffer();
	     Vector tokens = new Vector();
	     // split
	     char[] chars = splitStr.toCharArray();
	     for (int i=0; i < chars.length; i++) {
	         if (delimiter.indexOf(chars[i]) != -1) {
	             // we bumbed into a delimiter
	             if (token.length() > 0) {
	                 tokens.addElement(token.toString());
	                 token.setLength(0);
	             }
	         } else {
	             token.append(chars[i]);
	         }
	     }
	     // don't forget the "tail"...
	     if (token.length() > 0) {
	         tokens.addElement(token.toString());
	     }
	     // convert the vector into an array
	     String[] splitArray = new String[tokens.size()];
	     for (int i=0; i < splitArray.length; i++) {
	         splitArray[i] = (String)tokens.elementAt(i);
	     }
	     return splitArray;
	 }

	public static void main(String[] args)
	{
		String me = "1.2332,4567383,1.29238739,help me";
		
		String[] args1;
		
		args1 = SplitStrings.Split(me, ",");
		
		for(int i=0; i<args1.length; i++)
			System.out.println(args1[i]);
	}
	
	
}	
