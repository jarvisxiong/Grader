package grail.util;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import grail.tokens.MoveCommand;
import grail.tokens.ApproachCommand;
import grail.tokens.SayCommand;
import grail.tokens.CallCommand;
import grail.tokens.DefineCommand;
import grail.tokens.ProceedCommand;
import grail.tokens.RepeatCommand;
import grail.tokens.RotateLeftArmCommand;
import grail.tokens.RotateRightArmCommand;
import grail.tokens.SleepCommand;
import grail.tokens.ThreadCommand;
import grail.tokens.WaitCommand;
import grail.tokens.UndoCommand;
import grail.tokens.RedoCommand;
import grail.tokens.WordTokenBean;
import grail.tokens.AWordTokenBean;
import grail.tokens.ANumberTokenBean;
import grail.tokens.ATokenBean;
import grail.tokens.NumberTokenBean;
import grail.tokens.EndTokenBean;
import grail.tokens.StartTokenBean;
import grail.tokens.QuoteTokenBean;
import grail.tokens.TokenBean;
import grail.tokens.PlusTokenBean;
import grail.tokens.MinusTokenBean;


@SuppressWarnings("unused")
@Tags({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"ScannedString", "Tokens"})
@EditablePropertyNames({"ScannedString"})

public class ScannerBean implements StringScanner {
	private String scannedString;
	private TokenBean[] tokens;
	private TokenBean[] tempTokenStorage;
	
	public void setScannedString(String newScannedString) {
		scannedString = newScannedString;
		tempTokenStorage = new TokenBean[scannedString.length()];
		int numTokens = scanString();
		copyTokens(numTokens);
		tempTokenStorage = null;
	}
	
	public String getScannedString() {
		return scannedString;
	}
	
	public TokenBean[] getTokens() {
		return tokens;
	}

	/**
	 * scanString tests the Scanned String property one char at a time and creates
	 * tokens for each number, word, quote, +, -, {, and } encountered in the
	 * string.
	 * @return Returns number of tokens encountered.
	 */
	public int scanString() {
		int index = 0;
		int arrayIndex = 0;
		while (index < scannedString.length()) {
			char currentChar = scannedString.charAt(index);
			if (currentChar == '+') {
				tempTokenStorage[arrayIndex] = new PlusTokenBean(currentChar);
				arrayIndex++;
				index++;
			}
			else if (currentChar == '-') {
				tempTokenStorage[arrayIndex] = new MinusTokenBean(currentChar);
				arrayIndex++;
				index++;
			}
			else if (currentChar == '{') {
				tempTokenStorage[arrayIndex] = new StartTokenBean(currentChar);
				arrayIndex++;
				index++;
			}
			else if (currentChar == '}') {
				tempTokenStorage[arrayIndex] = new EndTokenBean(currentChar);
				arrayIndex++;
				index++;
			}
			else if (Character.isDigit(currentChar)) {
				int endIndex = getNumLength(scannedString, index);  
				tempTokenStorage[arrayIndex] = 
						new ANumberTokenBean(scannedString.substring(index, endIndex));
				arrayIndex++;
				index = endIndex;
			}
			else if (isLetter(currentChar)) {
				int endIndex = getWordLength(scannedString, index); 
				tempTokenStorage[arrayIndex] = 
						CommandFinder.findCommand(scannedString.substring(index, endIndex));
				arrayIndex++;
				index = endIndex;
			}
			else if (currentChar == '"') {
				int endIndex = getQuoteLength(scannedString, index); 
				if (endIndex == -1) { //Indicates error condition
					System.out.println("Error: Missing closing quotation marks.");
					index = scannedString.length(); //skips index to end
				}
				else { 
					tempTokenStorage[arrayIndex] = 
							new QuoteTokenBean(scannedString.substring(index + 1, endIndex));
					arrayIndex++;
					index = endIndex + 1;
				} 
			}
			else {
				index++;
			}
		} 
		return arrayIndex;
	}
	
	private static int getNumLength(String input, int startIndex) {
		int endIndex = startIndex + 1;
		while (endIndex < input.length() && 
				Character.isDigit(input.charAt(endIndex))) {
			endIndex++;
		}
		return endIndex;
	}
	
	private static int getWordLength(String input, int startIndex) {
		int endIndex = startIndex + 1;
		while (endIndex < input.length() && 
				isLetter(input.charAt(endIndex))) {
			endIndex++;
		}  
		return endIndex;
	}
	
	private static int getQuoteLength(String input, int startIndex) {
		int endIndex = startIndex + 1;
		while (endIndex < input.length() && 
				input.charAt(endIndex) != '"') {
			endIndex++;
		}
		if (endIndex >= input.length()) { 			//Error condition - reaches end of string 
			return -1;                   			//before second quotation mark 
		}
		return endIndex;
	}
	
	private static boolean isLetter(char currentChar) {
		if (currentChar >= 'A' && currentChar <= 'Z' ||
				currentChar >= 'a' && currentChar <= 'z') {
			return true;
		}
		return false;
	}
	private void copyTokens(int arraySize) { 
		tokens = new TokenBean[arraySize];
		for (int index = 0; index <= arraySize - 1; index++) {
			tokens[index] = tempTokenStorage[index];
		}
	}
}
