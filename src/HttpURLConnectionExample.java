import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.DatatypeConverter;



public class HttpURLConnectionExample {
	private final String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws Exception {
		HttpURLConnectionExample http = new HttpURLConnectionExample();
		
		System.out.println("Testing 1 -  Send address GET request");
		http.sendGet();
		
	}
	
	// Address GET request
	private void sendGet() throws Exception{
		/*
		 String url = "http://interntest.herokuapp.com/get?email=carol.mf14@gmail.com";

		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//
		con.setRequestMethod("GET");
		
		//request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL: "+ url);
		System.out.println("Response Code : " + responseCode);
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();

		//print result
		System.out.println(response.toString());
        
		//CONVERTIONS
		JSONObject message = new JSONObject(response.toString());

		// get message on base64 and print on screen
		//String messageValue = new String(message.getString("value"));
		 */
		
		String messageValue = "xlactz3Ja8Z/qep6niE=";
		//String messageValue = "xlap6nqeIQ==";
		System.out.println("\nBase64 = "+ messageValue);
		
		// convert message base64 to hex and print on screen DONE
		String hex = DatatypeConverter.printHexBinary(DatatypeConverter.parseBase64Binary(messageValue));
		System.out.println("\nHex = " + hex);

		// convert message hex to binary and print on screen DONE
		String binary = hexToBin(hex);
		System.out.println("\nBinary = " + binary);
		
		// formated DONE
		String result = insertCharacterForEveryNDistance(8, binary, ' ');
		System.out.println("\nBinary formated = " + result);
		
		// remove groups DONE
		String withoutGroups =  removeGroups(result);
		System.out.println("\nString without groups = " + withoutGroups);
	
		// binary with 5 characters
		// first remove blank spaces DONE
		String stringWithoutSpaces = removeWhiteSpaces(withoutGroups);
		System.out.println("\nString without spaces = " + stringWithoutSpaces);
		
		// now separate at 5 DONE
		String binary5 = insertCharacterForEveryNDistance(5, stringWithoutSpaces, ' ');
		System.out.println("\nString separated 5 to 5 = " + binary5);
		
		// decode the message DONE
		String decodedAccordingTable = decodeAccordingToTable(binary5);
		System.out.println("\nDecoded according to the table = " + decodedAccordingTable);
		
		// remove blank spaces again DONE
		String futureBinary8 = removeWhiteSpaces(decodedAccordingTable);
		System.out.println("\nRemove spaces again = " + futureBinary8);
		
		// separate at 8 DONE
		String newBinary8 = insertCharacterForEveryNDistance(8, futureBinary8, ' ');
		System.out.println("\nBinary 8 = " + newBinary8);
		
		// convert to text to read the message DONE
		String mensagem =  binaryToText(newBinary8);
		System.out.println("\nMessage = " + mensagem);
		
		// número de espaços em branco na String DONE
		int spacesOfString = countBlankSpaces(mensagem);

		// 5.5 - reverse the message DONE
		String reverseString = reverse(mensagem);
		System.out.println("\nInvertedmessage = " + reverseString);
		
		// número de espaços em branco na String invertida DONE
		int spacesOfInvertedString = countBlankSpaces(reverseString);
		
		// remove blank spaces from reverse string DONE
		String reverseStringWithoutSpaces = removeWhiteSpaces(reverseString);
		System.out.println("\nInverted string without spaces = " + reverseStringWithoutSpaces);
		
		
		// convertendo a String invertida sem espaços para uma string de binário DONE
		String invertedMessageBinary = invertedToBinary(reverseStringWithoutSpaces);
		System.out.println("\n BINÁRIO DA STRING INVERTIDA = " + invertedMessageBinary);
		
		// removendo espaços em branco DONE
		String noBlankSpaces = removeWhiteSpaces(invertedMessageBinary);
		System.out.println("\nString sem espaços = " + noBlankSpaces);
		
		// separando de 8 em 8 DONE
		String separated = insertCharacterForEveryNDistance(8, noBlankSpaces, ' ');
		System.out.println("\nString separada de 8 em 8 = "+separated);
		
		// adicionando espaços ao binário da string invertida  DONE
		String invertedBinaryMessageWithBlankSpaces = addingSpaces(separated, spacesOfInvertedString);
		System.out.println("\nbinário com espaços no final = " + invertedBinaryMessageWithBlankSpaces);
		
		// remover espaços em branco DONE
		String withoutSpaces = removeWhiteSpaces(invertedBinaryMessageWithBlankSpaces);
		System.out.println("\nSem separações = " + withoutSpaces);
		
		// separar de  4 em 4 DONE
		String separated4to4 = insertCharacterForEveryNDistance(4, withoutSpaces, ' ');
		System.out.println("\nString separada de 4 em 4 = "+ separated4to4);
				
		// converter de acordo coma tabela de 4 para 5 DONE
		String de4para5 = encodeAccordingToTable(separated4to4);
		System.out.println("\nde 4 bits para 5 = " + de4para5);
		
		// separate at 5 NÃO PRECISA
		String de5em5 = insertCharacterForEveryNDistance(5, de4para5, ' ');
		System.out.println("\nde 5 em 5 = " + de5em5);
		
		// separate at 8 DONE
		String de8em8 = insertCharacterForEveryNDistance(8, de4para5, ' ');
		System.out.println("\nde 8 em 8 = " + de8em8);
		
		
		// adicionando os grupos da tabela DONE
		String invertedEncoded = addingGroups(de8em8);
		System.out.println("\nDe para 4 para 5 de acordo com a tabela =  "+invertedEncoded);
		
		//separar de 8 em 8 DONE
		String separated8to8 = insertCharacterForEveryNDistance(8, invertedEncoded, ' ');
		System.out.println("\nString separada de 8 em 8 = "+ separated8to8);
		
		// COMEÇA A DAR RUIM 
		
		String mensagem2 =  binaryToText(separated8to8);
		System.out.println("\nmensagem = " + mensagem2);
				
		byte[] encoded = Base64.getEncoder().encode(mensagem2.getBytes());
		System.out.println(new String(encoded));
		
	}
	
	public static String hexToBin(String s) {
	    String preBin = new BigInteger(s, 16).toString(2);
	    Integer length = preBin.length();
	    if (length < 8) {
	        for (int i = 0; i < 8 - length; i++) {
	            preBin = "0" + preBin;
	        }
	    }
	    return preBin;
	}
	
	public static String insertCharacterForEveryNDistance(int distance, String original, char c){
	    StringBuilder sb = new StringBuilder();
	    
	    char[] charArrayOfOriginal = original.toCharArray();
	    
	    for(int ch = 0 ; ch < charArrayOfOriginal.length ; ch++){
	        if (ch == 0){
	        	sb.append(charArrayOfOriginal[ch]);
	        } else if(ch % distance == 0){
	            sb.append(c).append(charArrayOfOriginal[ch]);
	        }else {
	            sb.append(charArrayOfOriginal[ch]);
	        }
	    }
	    return sb.toString();
	}
	
	public static String removeGroups(String formatedBinary){
		
		String newString = formatedBinary.replaceAll("11000110 ", "").replaceAll("01101011 ","").replace(" 00100001","");
		
		return newString;
	}
	
	public static String removeWhiteSpaces(String binary8){
		String newString =  new String ();
		
		newString =  binary8.replaceAll("\\s","");
		
		return newString;
	}
	
	public static String decodeAccordingToTable(String binary5){
		String decoded = binary5.replaceAll("11110", "0000").replaceAll("01001", "0001")
						.replaceAll("10100", "0010").replaceAll("10101", "0011").replaceAll("01010", "0100")
						.replaceAll("01011", "0101").replaceAll("01110", "0110").replaceAll("01111", "0111")
						.replaceAll("10010", "1000").replaceAll("10011", "1001").replaceAll("10110", "1010")
						.replaceAll("10111", "1011").replaceAll("11010", "1100").replaceAll("11011", "1101")
						.replaceAll("11100", "1110").replaceAll("11101", "1111");
				
		return decoded; 
	}
	
	public static String encodeAccordingToTable(String separated4to4){
		String [] array =  separated4to4.split(" ");
		StringBuilder newString =  new StringBuilder();
		
		int i = 0;
		
		for (i = 0; i < array.length; i++){
			//System.out.println(array[i]);
		}
		
		int groups = i ;
		
		for (i = 0; i < groups; i++){
			switch (array[i]) {
			case "0000":
				newString.append("11110");
				break;
			case "0001":
				newString.append("01001");
				break;
			case "0010":
				newString.append("10100");
				break;
			case "0011":
				newString.append("10101");
				break;
			case "0100":
				newString.append("01010");
				break;
			case "0101":
				newString.append("01011");
				break;
			case "0110":
				newString.append("01110");
				break;
			case "0111":
				newString.append("01111");
				break;
			case "1000":
				newString.append("10010");
				break;
			case "1001":
				newString.append("10011");
				break;
			case "1010":
				newString.append("10110");
				break;
			case "1011":
				newString.append("10111");
				break;
			case "1100":
				newString.append("11010");
				break;
			case "1101":
				newString.append("11011");
				break;
			case "1110":
				newString.append("11100");
				break;
			case "1111":
				newString.append("11101");
				break;
			default:
				break;
			}
			
		}
		return newString.toString();
	}
	
	public static String addingGroups(String binaryOf8bits){
		int i = 0;
		String[] array = binaryOf8bits.split(" ");
		
		StringBuilder newString =  new StringBuilder();
		
		for (i = 0; i < array.length; i ++){
			//System.out.println("\nXXX: " + array[i]);
		}
		
		System.out.println("\nTAMANHO = " + array.length);
		
		for (i = 0; i < array.length; i+=5){
			//start group
			newString.append("11000110");
			
			newString.append(array[i]);
			newString.append(array[i+1]);
			newString.append(array[i+2]);
			newString.append(array[i+3]);
			newString.append(array[i+4]);
			
			if((i + 5) < array.length){
				//end group
				newString.append("01101011");
			} else {
				// end of communication
				newString.append("00100001");	
			} 
		}
		return newString.toString();
	}
	
	public static String binaryToText(String string){
	    StringBuilder sb = new StringBuilder();
	    
	    char[] chars = string.replaceAll("\\s", "").toCharArray();
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
	        sb.append(Character.toChars(sum));
	    }
	    return sb.toString();
	}
	
	public static String reverse(String stringMessage) {
	   StringBuilder builder = new StringBuilder(stringMessage);
	   String reverse = builder.reverse().toString();
	   return reverse;    
	}
	
	public static String reverseMessageToBinary(String reverseMessage){
		byte[] bytes = reverseMessage.getBytes();
		
		  StringBuilder binary = new StringBuilder();
		  for (byte b : bytes)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     binary.append(' ');
		  }
		  //System.out.println("'" + reverseMessage + "' to binary: " + binary);
		  
		  return binary.toString();
	}
	
	// keeping the binary spaces of string
	public static int  countBlankSpaces(String newbinary8){
		int i = 0;
		
		Pattern p = Pattern.compile(" ");
			
		Matcher m = p.matcher( newbinary8 );
		
		while (m.find()) {
		    i++;
		}
		
		System.out.println("\nquantidade de espaços em branco  = " + i);
		return i;
	}
	
	// mensagem invertida para binário 
	public static String invertedToBinary(String inverted){
		byte[] bytes = inverted.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes){
		   int val = b;
		   for (int i = 0; i < 8; i++){
		      binary.append((val & 128) == 0 ? 0 : 1);
		      val <<= 1;
		   }
		   binary.append(' ');
		}
		//System.out.println("'" + inverted + "' to binary: " + binary.toString());
		return binary.toString();	
	}
	
	public static String addingSpaces(String invertedBinary, int times){
		StringBuilder invertedWithSpaces = new StringBuilder(invertedBinary);
		
		for (int i = 0; i < times; i++){
			invertedWithSpaces.append(" 00100000");
		}
		
		return invertedWithSpaces.toString();
	}
	
	// array de bytes
	public static String[] toArray(String recived){
		String [] array =  recived.split(" ");
		
		int i = 0;
		
		for (i = 0; i < array.length; i++){
			//System.out.println(array[i]);
		}
		
		return array;
	}
}
	