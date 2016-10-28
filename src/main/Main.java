package main;

import comunnication.Communication;
import conversions.Conversions;
import conversions.Formatting;

public class Main {
	public static void main(String[] args) throws Exception {
		final int byte8 = 8;
		final int byte5 = 5;
		final int byte4 = 4;
		
		//String messageString = Communication.sendGet();
		//System.out.println("\nmessage = " + messageString);
		String messageString = "xlactz3Ja8Z/qep6niE=";
		
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
		
		int firstMessageBlankSpaces = Formatting.countBlankSpaces(firstMessage);
		System.out.println("\nblank spaces = " + firstMessageBlankSpaces);
		
		String invertedString = Conversions.reverse(firstMessage);
		System.out.println("\ninverted message = " + invertedString);
		
		int invertedStringBlankSpaces = Formatting.countBlankSpaces(invertedString);
		System.out.println("\nblank spaces = " + invertedStringBlankSpaces);
		
		String invertedStringWihtoutSpaces = new String();
		
		if (firstMessageBlankSpaces == invertedStringBlankSpaces){
			invertedStringWihtoutSpaces = Formatting.removeBlankSpaces(invertedString);	
		} else {
			// Nothing to do
		}
		System.out.println("\ninverted message = " + invertedStringWihtoutSpaces);
		
		String invertedToBinary = Conversions.invertedToBinary(invertedStringWihtoutSpaces);
		System.out.println("\ninverted binary = " + invertedToBinary);
		
		String invertedStringBinaryWithoutSpaces = Formatting.removeBlankSpaces(invertedToBinary);
		System.out.println("\ninverted binary wihtout spaces = " + invertedStringBinaryWithoutSpaces);
		
		String invertedStringBinary8 = Formatting.insertCharacter(byte8, invertedStringBinaryWithoutSpaces, ' ');
		System.out.println("\ninverted formated 8 = " + invertedStringBinary8);
		
		String invertedWithBinaryBlankSpaces = Formatting.addingSpaces(invertedStringBinary8, invertedStringBlankSpaces);
		System.out.println("\ninverted binary with spaces at the end = " + invertedWithBinaryBlankSpaces);
		
		String invertedWithouSpaces = Formatting.removeBlankSpaces(invertedWithBinaryBlankSpaces);
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
		
		// ERROR
		String invertedMessage = Conversions.binaryToText(binary8StringFinal);
		System.out.println("\ninverted message as text = " + invertedMessage);
		
		byte[] invertedBinary64 = Conversions.textToBase64(invertedMessage);
		System.out.println(new String(invertedBinary64));	
	}

}
