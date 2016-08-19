package mp.bean;

import mp.tokens.Token;
import mp.tokens.ABasicToken;
import mp.tokens.AMinusToken;
import mp.tokens.ANumToken;
import mp.tokens.APlusToken;
import mp.tokens.AQuoteToken;
import mp.tokens.StartToken;
import mp.tokens.AValueToken;
import mp.tokens.AWordToken;
import mp.tokens.EndToken;
import mp.tokens.ApproachToken;
import mp.tokens.CallToken;
import mp.tokens.DefineToken;
import mp.tokens.FailToken;
import mp.tokens.MoveToken;
import mp.tokens.PassToken;
import mp.tokens.ProceedAllToken;
import mp.tokens.RedoToken;
import mp.tokens.RepeatToken;
import mp.tokens.RotateLeftArmToken;
import mp.tokens.RotateRightArmToken;
import mp.tokens.SayToken;
import mp.tokens.SleepToken;
import mp.tokens.ThreadToken;
import mp.tokens.UndoToken;
import mp.tokens.WaitToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags({"Bean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"ScannedString", "Tokens", "Error"})
@EditablePropertyNames({"ScannedString"})

public class AScanner implements Scanner {
	
	public AScanner() {}
	public AScanner(String initInput){ setScannedString(initInput); }
	
	Token[] tokensLarge, tokens;
	Character[] errorsLarge, errors; //creates Character array for storing illegal characters
	String input;
	int errorIdx, arrayNo;
	public String getScannedString() { //for convention
		return input;
	}
	public void setScannedString(String inLine) {
		input = inLine;
		if (inLine == "" || inLine.charAt(0) != '.') {
			int charNo = 0;
			arrayNo = 0;
			errorIdx = 0;
			char c;
			inLine += "  "; //adds space to end of the input string to prevent index out of bounds throw
			String token;
			tokensLarge = new Token[inLine.length()];
			errorsLarge = new Character[inLine.length()];
			while (charNo < inLine.length()) {
				c = inLine.charAt(charNo);
				isIllegalCharacter(c); //checks to see if character is illegal and stores it in "errors" if it is
				if (Character.isDigit(c)) { //determines start of number token
					int numStartIndex = charNo;
					int numEndIndex = getNumberLength(inLine, numStartIndex);
					token = inLine.substring(numStartIndex, numEndIndex);
					tokensLarge[arrayNo] = new ANumToken(token);
					arrayNo++;
					charNo = numEndIndex-1;
				}
				else if (myIsLetter(c)) {
					int wordStartIndex = charNo;
					int wordEndIndex = getWordLength(inLine, wordStartIndex);
					token = inLine.substring(wordStartIndex, wordEndIndex);
					findCommand(token);
					charNo = wordEndIndex-1;
				}
				else if (inLine.charAt(charNo) == '"'){
					int qsStartIndex = charNo + 1; //starts index at character after start quote
					int qsEndIndex = getQuotedStringLength(inLine, qsStartIndex);
					token = inLine.substring((qsStartIndex), qsEndIndex);
					tokensLarge[arrayNo] = new AQuoteToken(token);
					arrayNo++;
					charNo = qsEndIndex;
				}
				else if (c == '+') {
					token = Character.toString(c);
					tokensLarge[arrayNo] = new APlusToken(token);
					arrayNo++;
				}
				else if (c == '-'){
					token = Character.toString(c);
					tokensLarge[arrayNo] = new AMinusToken(token);
					arrayNo++;
				}
				else if (c == '{'){
					token = Character.toString(c);
					tokensLarge[arrayNo] = new StartToken(token);
					arrayNo++;
				}
				else if (c == '}'){
					token = Character.toString(c);
					tokensLarge[arrayNo] = new EndToken(token);
					arrayNo++;
				}
				charNo++;
			}
			int errorSize = arraySize(errorsLarge);
			errors = copyRange(errorsLarge, errorSize);
			int size = arraySize(tokensLarge);
			tokens = copyRange(tokensLarge, size);
		}
		else { //terminates the program
			System.out.println("Terminating character entered - terminating program.");
		}
	}
	public Token[] getTokens(){
		return tokens;
	}
	public Character[] getError(){
		return errors;
	}
	@Visible(false)
	public int getNumberLength(String inLine, int numStartIndex){
		int index = numStartIndex;
		while (Character.isDigit(inLine.charAt(index))) {
			++index; }
		return index;	
	}
	@Visible(false)
	public int getWordLength(String inLine, int wordStartIndex){
		int index = wordStartIndex;
		while (myIsLetter(inLine.charAt(index))) {
			++index; }
		return index;	
	}
	@Visible(false)
	public int getQuotedStringLength(String inLine, int qsStartIndex){
		int index = qsStartIndex; 
		while (index < inLine.length()) {
			if (inLine.charAt(index) != '"') {
				if (index == (inLine.length()-1)){
					System.out.println("ERROR: Closing double quote is missing!\n"
							+ "Good thing my program is smarter than you are!\n"
							+ "Here is your \"quoted\" string anyway:"); }
			index++; 
			}
			else {
				break;
			}
		}
		return index;
	}
	
	public boolean myIsLetter(char aChar) {
		
		if ((aChar >= 'a' && aChar <= 'z') || (aChar >= 'A' && aChar <= 'Z')) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void isIllegalCharacter(char charAtIndex){ //stores character in error string if illegal character is entered
		char c = charAtIndex;
		if (c != ' ' && c != '-' && c != '+' && c != '"' && Character.isDigit(c) == false &&
				myIsLetter(c) == false && c != '{' && c != '}')  {
			errorsLarge[errorIdx] = c; 
			errorIdx++;}
	}

	public void findCommand(String token){
		switch (token.toLowerCase()) {
		case "move":
			tokensLarge[arrayNo] = new MoveToken(token);
			arrayNo++;
			break;
		case "say":
			tokensLarge[arrayNo] = new SayToken(token);
			arrayNo++;
			break;
		case "rotateleftarm":
			tokensLarge[arrayNo] = new RotateLeftArmToken(token);
			arrayNo++;
			break;
		case "rotaterightarm":
			tokensLarge[arrayNo] = new RotateRightArmToken(token);
			arrayNo++;
			break;
		case "repeat":
			tokensLarge[arrayNo] = new RepeatToken(token);
			arrayNo++;
			break;
		case "define":
			tokensLarge[arrayNo] = new DefineToken(token);
			arrayNo++;
			break;
		case "call":
			tokensLarge[arrayNo] = new CallToken(token);
			arrayNo++;
			break;
		case "thread":
			tokensLarge[arrayNo] = new ThreadToken(token);
			arrayNo++;
			break;
		case "wait":
			tokensLarge[arrayNo] = new WaitToken(token);
			arrayNo++;
			break;
		case "proceedall":
			tokensLarge[arrayNo] = new ProceedAllToken(token);
			arrayNo++;
			break;
		case "sleep":
			tokensLarge[arrayNo] = new SleepToken(token);
			arrayNo++;
			break;
		case "undo":
			tokensLarge[arrayNo] = new UndoToken(token);
			arrayNo++;
			break;
		case "redo":
			tokensLarge[arrayNo] = new RedoToken(token);
			arrayNo++;
			break;
		case "approach":
			tokensLarge[arrayNo] = new ApproachToken(token);
			arrayNo++;
			break;
		case "passed":
			tokensLarge[arrayNo] = new PassToken(token);
			arrayNo++;
			break;
		case "failed":
			tokensLarge[arrayNo] = new FailToken(token);
			arrayNo++;
			break;
		default:
			tokensLarge[arrayNo] = new AWordToken(token);
			arrayNo++;
			break;
		}
	}
	public int arraySize(Token[] largeArray){
		int index = 0;
		while(largeArray[index] != null){
			index++;
		}
		return index;
	}
	public int arraySize(Character[] charArray){
		int index = 0;
		while(charArray[index] != null){
			index++;
		}
		return index;
	}
	
	public Token[] copyRange(Token[] largeArray, int end){
		Token[] newArray = new Token[end];
		for (int i = 0; i < end; i++) {
			newArray[i] = largeArray[i];
		}
		return newArray;
	}
	public Character[] copyRange(Character[] largeArray, int end){
		Character[] newArray = new Character[end];
		for (int i = 0; i < end; i++) {
			newArray[i] = largeArray[i];
		}
		return newArray;
	}

	
}
