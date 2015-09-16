package main;

//import util.annotations.EditablePropertyNames; 
//import util.annotations.PropertyNames;
//import util.annotations.StructurePattern; 
//import util.annotations.StructurePatternNames; 
//import util.annotations.Tags;

//@Tags({"ScannerBean"}) 
//@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
//@PropertyNames({"ScannedString"}) 
//@EditablePropertyNames({"ScannedString"})

public class BeanA2 {
	
	static StringBuffer errors = new StringBuffer(); //creates String for storing illegal characters
	
	public void getScannedString() { //for convention
	}
	
	public void setScannedString(String inLine) {
		int charNo = 0;
		inLine += " "; //adds space to end of the input string to prevent index out of bounds throw
		String token;
		errors.setLength(0);
		while (charNo < inLine.length()) {
			isIllegalCharacter(inLine.charAt(charNo));
			if (Character.isDigit(inLine.charAt(charNo))) { //determines start of number token
				int numStartIndex = charNo;
				int numEndIndex = getNumberLength(inLine, numStartIndex);
				token = inLine.substring(numStartIndex, numEndIndex);
				System.out.println("number: " + token);
				charNo = numEndIndex;
			}
			else if (myIsLetter(inLine.charAt(charNo))) {
				int wordStartIndex = charNo;
				int wordEndIndex = getWordLength(inLine, wordStartIndex);
				token = inLine.substring(wordStartIndex, wordEndIndex);
				System.out.println("word: " + token);
				charNo = wordEndIndex;
			}
			else if (inLine.charAt(charNo) == '"'){
				int qsStartIndex = charNo;
				int qsEndIndex = getQuotedStringLength(inLine, qsStartIndex);
				token = inLine.substring((qsStartIndex+1), qsEndIndex);
				System.out.println("quoted string: " + token);
				charNo = qsEndIndex;
			}
			else if (inLine.charAt(charNo) == '+' || inLine.charAt(charNo) == '-'){
				token = Character.toString(inLine.charAt(charNo));
				System.out.println("sign: " + token);
			}
			charNo++;
		}
		if (errors.length() != 0){
			errors.delete(errors.length()-2, errors.length()-1); //deletes last comma that is unnecessary
			System.out.println("Illegal characters entered: " + errors); }
	}
	
	public static int getNumberLength(String inLine, int numStartIndex){
		int index = numStartIndex;
		while (Character.isDigit(inLine.charAt(index))) {
			++index; }
		return index;	
	}
	
	public static int getWordLength(String inLine, int wordStartIndex){
		int index = wordStartIndex;
		while (myIsLetter(inLine.charAt(index))) {
			++index; }
		return index;	
	}
	
	public static int getQuotedStringLength(String inLine, int qsStartIndex){
		int index = qsStartIndex + 1; //starts index at character after start quote
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
	
	public static boolean myIsLetter(char aChar) {
		if ((aChar >= 'a' && aChar <= 'z') || (aChar >= 'A' && aChar <= 'Z')) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void isIllegalCharacter(char charAtIndex){ //stores character in error string if illegal character is entered
		char c = charAtIndex;
		if (c != ' ' && c != '-' && c != '+' && c != '"' && (Character.isDigit(c) && myIsLetter(c)) != true)  {
			errors.append("\"" + c + "\", "); }
	}
}
