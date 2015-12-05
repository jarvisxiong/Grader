package grail;

import main.Assignment10;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({ "ScannerBean" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "ScannedString", "Token"})
@EditablePropertyNames({ "ScannedString" })
public class ScannerBean implements ScannerInterface {

	private String scannedString = null;
	final int maxTokens = 25;
	private Token largeArray[] = null;
	private Token compactArray[] = null;

	public String getScannedString() {
		return scannedString;
	}

	public Token[] getToken() {
		return compactArray;
	}

	public void setScannedString(String aString) {
		largeArray = new Token[maxTokens];
		scannedString = aString;
		int index = 0;
		int tokenFinish = 0;
		int arrayCount = 0;
		scannedString += " ";
		while (index < scannedString.length()) {
			char nextChar = scannedString.charAt(index);
			if (nextChar == '+') {
				Token aPlus = new APlus(scannedString.substring(index,index + 1));
				largeArray[arrayCount] = aPlus;
				arrayCount++;
				index++;
			} else if (nextChar == '-') {
				Token aMinus = new AMinus(scannedString.substring(index,
						index + 1));
				largeArray[arrayCount] = aMinus;
				arrayCount++;
				index++;
			} else if (nextChar == '{') {
				Token aStart = new AStart(scannedString.substring(index,
						index + 1));
				largeArray[arrayCount] = aStart;
				arrayCount++;
				index++;
			} else if (nextChar == '}') {
				Token anEnd = new AnEnd(scannedString.substring(index,
						index + 1));
				largeArray[arrayCount] = anEnd;
				arrayCount++;
				index++;
			} else if (Character.isDigit(nextChar)) {
				tokenFinish = Assignment10.getDigitLength(scannedString, index);
				Number aNumber = new ANumber(scannedString.substring(index,
						tokenFinish));
				largeArray[arrayCount] = aNumber;
				arrayCount++;
				index = tokenFinish;
				index++;
			} else if (nextChar == ' ') {
				index++;
			}

			else if (Character.isLetter(nextChar)) {
				tokenFinish = Assignment10.getWordLength(scannedString, index);
				String testString = scannedString.substring(index, tokenFinish);
				if ("move".equalsIgnoreCase(testString)) {
					Token move = new Move(testString);
					largeArray[arrayCount] = move;
					arrayCount++;
				} else if ("say".equalsIgnoreCase(testString)) {
					Token say = new Say(testString);
					largeArray[arrayCount] = say;
					arrayCount++;
				} else if ("rotateLeftArm".equalsIgnoreCase(testString)) {
					Token rotateLeft = new RotateLeftArm(testString);
					largeArray[arrayCount] = rotateLeft;
					arrayCount++;
				} else if ("rotateRightArm".equalsIgnoreCase(testString)) {
					Token rotateRight = new RotateRightArm(testString);
					largeArray[arrayCount] = rotateRight;
					arrayCount++;
				} else if ("repeat".equalsIgnoreCase(testString)) {
					Token repeat = new Repeat(testString);
					largeArray[arrayCount] = repeat;
					arrayCount++;
				} else if ("define".equalsIgnoreCase(testString)) {
					Token define = new Define(testString);
					largeArray[arrayCount] = define;
					arrayCount++;
				} else if ("call".equalsIgnoreCase(testString)) {
					Token call = new Call(testString);
					largeArray[arrayCount] = call;
					arrayCount++;
				} else if ("thread".equalsIgnoreCase(testString)) {
					Token thread = new Thread(testString);
					largeArray[arrayCount] = thread;
					arrayCount++;
				} else if ("wait".equalsIgnoreCase(testString)) {
					Token wait = new Wait(testString);
					largeArray[arrayCount] = wait;
					arrayCount++;
				} else if ("proceedAll".equalsIgnoreCase(testString)) {
					Token proceed = new ProceedAll(testString);
					largeArray[arrayCount] = proceed;
					arrayCount++;
				} else if ("undo".equalsIgnoreCase(testString)) {
					Token undo = new Undo(testString);
					largeArray[arrayCount] = undo;
					arrayCount++;
				} else if ("redo".equalsIgnoreCase(testString)) {
					Token redo = new Redo(testString);
					largeArray[arrayCount] = redo;
					arrayCount++;
				} else if ("sleep".equalsIgnoreCase(testString)) {
					Token sleep = new Sleep(testString);
					largeArray[arrayCount] = sleep;
					arrayCount++;
				} else if ("approach".equalsIgnoreCase(testString)) {
					Token approach = new Approach(testString);
					largeArray[arrayCount] = approach;
					arrayCount++;
				} else {
					Word aWord = new AWord(scannedString.substring(index,
							tokenFinish));
					largeArray[arrayCount] = aWord;
					arrayCount++;
				}
				index = tokenFinish;
				index++;
			} else if (nextChar == '"') {
				index++;
				tokenFinish = Assignment10.getQuotedString(scannedString, index);
				Token aQuote = new AQuote(scannedString.substring(index,
						tokenFinish));
				largeArray[arrayCount] = aQuote;
				arrayCount++;
				index = tokenFinish;
				index += 2;
			} else {
				index++;
			}
		}
		int tokenNumber = 0;
		while (largeArray[tokenNumber] != null) {
			tokenNumber++;
		}
		compactArray = new Token[tokenNumber];
		for (int i = 0; i < tokenNumber; i++) {
			compactArray[i] = largeArray[i];
		}
	}

}
