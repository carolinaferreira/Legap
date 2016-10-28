package main;

import java.math.BigInteger;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;
import comunnication.Communication;
import conversions.Conversions;
import conversions.Formatting;

public class Main {
	public static void main(String[] args) throws Exception {
		final int byte8 = 8;
		final int byte5 = 5;
		final int byte4 = 4;
		
		JSONObject object = Communication.sendGet();
		String messageString = object.getString("value");
		
		//String messageString = "xlactz3Ja8Z/qep6niE=";
		
		System.out.println("\nmessage string = " + messageString);
		
		String hexadecimalString = Conversions.base64ToHex(messageString);
		System.out.println("\nhexa = " + hexadecimalString);
		
		String binaryString = Conversions.hexToBin(hexadecimalString) ;
		System.out.println("\nbinary = " + binaryString);
		
		String binaryFormated = Formatting.insertCharacter(byte8, binaryString, ' ');
		System.out.println("\nbinary  formated = " + binaryFormated);
		
		String binaryStringWithoutGroups = Formatting.removeGroups(binaryFormated);
		System.out.println("\nbinary without groups = " + binaryStringWithoutGroups);
		
		String binaryStringWithoutSpaces = Formatting.removeBlankSpaces(binaryStringWithoutGroups);
		System.out.println("\nbinary without blank spaces = " + binaryStringWithoutSpaces);
		
		String binary5String = Formatting.insertCharacter(byte5, binaryStringWithoutSpaces, ' ');
		System.out.println("\nbinary 5 = " + binary5String);
		
		String binary5StringDecoded = Conversions.decodeAccordingToTable(binary5String);
		System.out.println("\nbinary decoded = " + binary5StringDecoded);
		
		String binary8StringWithoutSpaces =  Formatting.removeBlankSpaces(binary5StringDecoded);
		System.out.println("\nbinary 8 = " + binary8StringWithoutSpaces);
		
		String binary8String = Formatting.insertCharacter(byte8, binary8StringWithoutSpaces, ' ');
		System.out.println("\nbinary  8  formated = " + binary8String);
		
		String firstMessage =  Conversions.binaryToText(binary8String);
		System.out.println("\nmessage = " + firstMessage);
		firstMessage = firstMessage.trim();
			
		String invertedString = Conversions.reverse(firstMessage);
		System.out.println("\ninverted message = " + invertedString);
		
		String invertedStringWithBlankSpaces = Formatting.addingBlankSpaces(invertedString);
		System.out.println("\ninverted message with blank spaces = " + invertedStringWithBlankSpaces);
			
		String invertedToBinary = Conversions.invertedToBinary(invertedStringWithBlankSpaces);
		System.out.println("\ninverted binary = " + invertedToBinary);
		
		String invertedStringBinaryWithoutSpaces = Formatting.removeBlankSpaces(invertedToBinary);
		System.out.println("\ninverted binary wihtout spaces = " + invertedStringBinaryWithoutSpaces);
		
		String invertedStringBinary8 = Formatting.insertCharacter(byte8, invertedStringBinaryWithoutSpaces, ' ');
		System.out.println("\ninverted formated 8 = " + invertedStringBinary8);
		
		String invertedWithouSpaces = Formatting.removeBlankSpaces(invertedStringBinary8);
		System.out.println("\ninverted without spaces = " + invertedWithouSpaces);
		
		String binary4InvertedMessage = Formatting.insertCharacter(byte4, invertedWithouSpaces, ' ');
		System.out.println("\ninverted binary 4 = " + binary4InvertedMessage);
		
		String invertedEnconde = Conversions.encodeAccordingToTable(binary4InvertedMessage);
		System.out.println("\ninverted encode = " + invertedEnconde);
		
		String binary8InvertedMessage = Formatting.insertCharacter(byte8, invertedEnconde, ' ');
		System.out.println("\nbinary 8 inverted message = " + binary8InvertedMessage);
		
		String binary8InvertedWithGroups = Formatting.addingGroups(binary8InvertedMessage);
		System.out.println("\nbinary 8 with groups = " + binary8InvertedWithGroups);
		
		String binary8StringFinal = Formatting.insertCharacter(byte8, binary8InvertedWithGroups, ' ');
		System.out.println("\nfinal binary 8 = " + binary8StringFinal);
		
		String binary8StringFinalWithoutSpaces =  Formatting.removeBlankSpaces(binary8StringFinal);
		
		String hexadecimal = new BigInteger(binary8StringFinalWithoutSpaces, 2).toString(16);
		System.out.println("\nhexa test = " + hexadecimal);
		
		String base64 = DatatypeConverter.printBase64Binary(DatatypeConverter.parseHexBinary(hexadecimal));
		System.out.println("\nbase64 tes = " + base64);
		
		object.put("value", base64);
		
		Communication.sendPost(object);
	}
}
