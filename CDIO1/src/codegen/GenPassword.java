package codegen;

import java.util.Random;

public class GenPassword implements IGenPassword {
	private Random ran = new Random();
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see codegen.ICodeGen#genCode()
	 * Rules:
	 * 1: At least 6 characters
	 * 1.2: Including at least 3 from the following categories: 
	 * - small letters (a-z)
	 * - capital letters (A-Z)
	 * - numbers (0-9)
	 * - special characters. 
	 * 1.2.1: Special characters: {'.', '-', '_', '+', '!', '?', '='}
	 */
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
	
	private char genSmallLetter() {
		return (char)genChar(97, 122);
	}
	
	private char genCapitalLetter() {
		return  (char)genChar(65, 90);
	}
	
	private char genNumber() {
		return (char)genChar(48,57);
	}
	
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
	
	private int genChar(int lowerBound, int upperBound) {
		return lowerBound + ran.nextInt(upperBound-lowerBound+1);
	}
	
	private String mixUpString(char[] inputArray) {
		String strCode = "";
		for (int i = 0; i < inputArray.length; i++) {
			strCode += inputArray[i];
		}
		return strCode;
	}
}
