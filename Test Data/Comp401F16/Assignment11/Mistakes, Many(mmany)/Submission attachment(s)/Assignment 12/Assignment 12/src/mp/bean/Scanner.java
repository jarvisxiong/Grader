package mp.bean;

import mp.tokens.Token;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@PropertyNames({"ScannedString", "Tokens", "Error"})
@EditablePropertyNames({"ScannedString"})

public interface Scanner {
	public String getScannedString();	
	public void setScannedString(String inLine);	
	public Token[] getTokens();	
	public Character[] getError();		
	public int getNumberLength(String inLine, int numStartIndex);	
	public int getWordLength(String inLine, int wordStartIndex);
	public int getQuotedStringLength(String inLine, int qsStartIndex);
	public boolean myIsLetter(char aChar);
	public void isIllegalCharacter(char charAtIndex);	
	public int arraySize(Token[] largeArray);
	public int arraySize(Character[] charArray);
	public Token[] copyRange(Token[] largeArray, int end);
	public Character[] copyRange(Character[] largeArray, int end);
}
