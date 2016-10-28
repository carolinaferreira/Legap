package conversions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatting {
	public static String insertCharacter(int distance, String message, char c){
	    StringBuilder finalString = new StringBuilder();
	    
	    char[] charArrayOriginal = message.toCharArray();
	    
	    for(int ch = 0 ; ch < charArrayOriginal.length ; ch++){
	        if (ch == 0){
	        	finalString.append(charArrayOriginal[ch]);
	        } else if(ch % distance == 0){
	            finalString.append(c).append(charArrayOriginal[ch]);
	        }else {
	            finalString.append(charArrayOriginal[ch]);
	        }
	    }
	    return finalString.toString();
	}
	
	public static String removeGroups(String message){	
		String finalString = message.replaceAll("11000110 ", "")
									.replaceAll("01101011 ","")
									.replace(" 00100001","");
		
		return finalString;
	}
	
	public static String removeBlankSpaces(String message){
		String finalString =  new String ();
		
		finalString =  message.replaceAll("\\s","");
		
		return finalString;
	}
	
	public static int countBlankSpaces(String message){
		int blankSpaces = 0;
		
		Pattern p = Pattern.compile(" ");
			
		Matcher m = p.matcher(message);
		
		while (m.find()) {
		    blankSpaces++;
		}
		
		return blankSpaces;
	}
	
	public static String addingBlankSpaces(String message){
		StringBuilder finalString = new StringBuilder(message);
		
		if (finalString.length()%4 == 0){
			// Nothing to do
		} else {
			while(!(finalString.length()%4 == 0)){
				finalString.append(" ");
			}
		}
		
		//StringBuilder finalString = new StringBuilder(message);
		
		//for (int i = 0; i < times; i++){
			//finalString.append(" 00100000");
		//}
		
		return finalString.toString();
	}
	
	public static String addingGroups(String message){
		int i = 0;
		String[] array = message.split(" ");
		
		StringBuilder finalString =  new StringBuilder();
		
//		for (i = 0; i < array.length; i ++){
//			System.out.println("\nXXX: " + array[i]);
//		}
		
//		System.out.println("\nTAMANHO = " + array.length);
		
		for (i = 0; i < array.length; i+=5){
			//start group
			finalString.append("11000110");
			
			finalString.append(array[i]);
			finalString.append(array[i+1]);
			finalString.append(array[i+2]);
			finalString.append(array[i+3]);
			finalString.append(array[i+4]);
			
			if((i + 5) < array.length){
				//end group
				finalString.append("01101011");
			} else {
				// end of communication
				finalString.append("00100001");	
			} 
		}
		return finalString.toString();
	}

}
