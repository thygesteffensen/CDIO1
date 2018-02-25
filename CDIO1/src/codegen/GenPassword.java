package codegen;

import java.util.Random;

public class GenPassword implements IGenPassword {
	private Random ran = new Random();
	
	@Override
	public String genPassword() {
		char[] code = new char[6];
		code[0] = genSmallLetter();
		code[1] = genCapitalLetter();
		code[2] = genNumber();
		code[3] = genSpecialChar();
		code[4] = genSmallLetter();
		code[5] = genCapitalLetter();
		return mixUpString(code);
	}
	
	/**
	* Generates a random small letter using genChar() method
	*/
	private char genSmallLetter() {
		return (char)genChar(97, 122);
	}
	
	/**
	* Generates a random capitel letter using genChar() method
	*/
	private char genCapitalLetter() {
		return  (char)genChar(65, 90);
	}
	
	/**
	* Generates a random number using genChar() method
	*/
	private char genNumber() {
		return (char)genChar(48,57);
	}
	
	/**
	* Generates a random special character using genChar() method.
	* This cannot be done like the other ones, because the special
	* characters does not follow each other in ASCII number.
	* we are therefore using a switch
	*/
	private char genSpecialChar() {
		switch (genChar(0,6)) {
		case 0:
			return (char) 46;
		case 1:
			return (char) 45;
		case 2:
			return (char) 95;
		case 3:
			return (char) 43;
		case 4:
			return (char) 33;
		case 5:
			return (char) 63;
		case 6:
			return (char) 61;
		default:
			return '1';
		}
	}
	
	/**
	* Generates a random integer between the given bounds using
	* the Random packages.
	*/
	private int genChar(int lowerBound, int upperBound) {
		return lowerBound + ran.nextInt(upperBound-lowerBound+1);
	}
	
	/**
	* Not yet implemented...
	*/
	private String mixUpString(char[] inputArray) {
		String strCode = "";
		for (int i = 0; i < inputArray.length; i++) {
			strCode += inputArray[i];
		}
		return strCode;
	}
}
