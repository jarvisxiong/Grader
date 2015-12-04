package main;

import util.annotations.Tags;
import util.annotations.Visible;
import grail.CallCommand;
import grail.DefineCommand;
import grail.End;
import grail.Minus;
import grail.MoveCommand;
import grail.Number;
import grail.Plus;
import grail.ProceedAllCommand;
import grail.Quote;
import grail.RedoCommand;
import grail.RepeatCommand;
import grail.RotateLeftArmCommand;
import grail.RotateRightArmCommand;
import grail.SayCommand;
import grail.SleepCommand;
import grail.Start;
import grail.ThreadCommand;
import grail.TokenInterface;
import grail.UndoCommand;
import grail.WaitCommand;
import grail.Word;
import grail.ApproachCommand;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@Tags ({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"ScannedString","Tokens"})
@EditablePropertyNames({"ScannedString"})

public class ScannerBean implements ScannerBeanInterface{
	private String[] scannedString;
	private String unparsedString;
	final int MAX_TOKENS = 100;
	
	TokenInterface[] maxTokens;
	TokenInterface[] tokens;
	
	public ScannerBean () {}
	public ScannerBean (String inputString) {setScannedString(inputString);}
	
	public void setScannedString(String input) {
		unparsedString = input;
		maxTokens = Assignment12.scanner(input);
		setTokens(maxTokens);
	}

	public void setTokens(TokenInterface[] newTokens) {
		int counter = 0;
		for (int tokenCount = 0; tokenCount < newTokens.length; tokenCount++) {
			if (newTokens[tokenCount] != null) {
				counter++;
			}
			else {break;}
		}
		tokens = new TokenInterface[counter];
		for (int x = 0; x < counter; x++) {
			tokens[x] = newTokens[x];
		}
	}
	
@Visible(false)
	public String[] getScannedStringArray () {return scannedString;}	
	public TokenInterface[] getTokens() {return tokens;}
	public String getScannedString() {return unparsedString;}
}
	
