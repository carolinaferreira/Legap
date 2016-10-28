package conversions;

import java.math.BigInteger;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

public class Conversions {
	public static String base64ToHex(String message){
		String hex = DatatypeConverter.printHexBinary(DatatypeConverter.parseBase64Binary(message));
		return hex;
	}
	
	public static String hexToBin(String message) {
	    String bin = new BigInteger(message, 16).toString(2);
	    Integer length = bin.length();
	    if (length < 8) {
	        for (int i = 0; i < 8 - length; i++) {
	            bin = "0" + bin;
	        }
	    } else {
	    	// Nothing to do.
	    }
	    return bin;
	}
	
	public static String binaryToText(String message){
	    StringBuilder finalString = new StringBuilder();
	    
	    char[] chars = message.replaceAll("\\s", "").toCharArray();
	    int [] mapping = {1,2,4,8,16,32,64,128};

	    for (int j = 0; j < chars.length; j+=8) {
	        int idx = 0;
	        int sum = 0;
	        for (int i = 7; i>= 0; i--) {
	            if (chars[i+j] == '1') {
	                sum += mapping[idx];
	            }
	            idx++;
	        }
	    //    System.out.println(sum);//debug
	        finalString.append(Character.toChars(sum));
	    }
	    return finalString.toString();
	}
	
	public static String decodeAccordingToTable(String message){
		String decoded = message.replaceAll("11110", "0000").replaceAll("01001", "0001")
						.replaceAll("10100", "0010").replaceAll("10101", "0011").replaceAll("01010", "0100")
						.replaceAll("01011", "0101").replaceAll("01110", "0110").replaceAll("01111", "0111")
						.replaceAll("10010", "1000").replaceAll("10011", "1001").replaceAll("10110", "1010")
						.replaceAll("10111", "1011").replaceAll("11010", "1100").replaceAll("11011", "1101")
						.replaceAll("11100", "1110").replaceAll("11101", "1111");
				
		return decoded; 
	}
	
	public static String reverse(String stringMessage) {
	   StringBuilder builder = new StringBuilder(stringMessage);
	   String reverse = builder.reverse().toString();
	   return reverse;    
	}
	
	public static String invertedToBinary(String message){
		byte[] bytes = message.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes){
		   int val = b;
		   for (int i = 0; i < 8; i++){
		      binary.append((val & 128) == 0 ? 0 : 1);
		      val <<= 1;
		   }
		   binary.append(' ');
		}
		return binary.toString();	
	}
	
	public static String encodeAccordingToTable(String message){
		String [] array =  message.split(" ");
		StringBuilder finalString =  new StringBuilder();
		
		int i = 0;
		
		for (i = 0; i < array.length; i++){
			//System.out.println(array[i]);
		}
		
		int groups = i ;
		
		for (i = 0; i < groups; i++){
			switch (array[i]) {
			case "0000":
				finalString.append("11110");
				break;
			case "0001":
				finalString.append("01001");
				break;
			case "0010":
				finalString.append("10100");
				break;
			case "0011":
				finalString.append("10101");
				break;
			case "0100":
				finalString.append("01010");
				break;
			case "0101":
				finalString.append("01011");
				break;
			case "0110":
				finalString.append("01110");
				break;
			case "0111":
				finalString.append("01111");
				break;
			case "1000":
				finalString.append("10010");
				break;
			case "1001":
				finalString.append("10011");
				break;
			case "1010":
				finalString.append("10110");
				break;
			case "1011":
				finalString.append("10111");
				break;
			case "1100":
				finalString.append("11010");
				break;
			case "1101":
				finalString.append("11011");
				break;
			case "1110":
				finalString.append("11100");
				break;
			case "1111":
				finalString.append("11101");
				break;
			default:
				break;
			}
			
		}
		return finalString.toString();
	}
	
	public static byte[] textToBase64(String message){
		byte[] encoded = Base64.getEncoder().encode(message.getBytes());
		return encoded;
	}
}
