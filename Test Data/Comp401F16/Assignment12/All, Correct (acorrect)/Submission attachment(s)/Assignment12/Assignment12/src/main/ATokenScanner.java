package main;

import mp.commands.ApproachCommand;
import mp.commands.CallCommand;
import mp.commands.DefineCommand;
import mp.commands.FailCommand;
import mp.commands.MoveCommand;
import mp.commands.PassCommand;
import mp.commands.ProceedAllCommand;
import mp.commands.RedoCommand;
import mp.commands.RepeatCommand;
import mp.commands.RotateLeftArmCommand;
import mp.commands.RotateRightArmCommand;
import mp.commands.SayCommand;
import mp.commands.SleepCommand;
import mp.commands.ThreadCommand;
import mp.commands.UndoCommand;
import mp.commands.WaitCommand;
import mp.tokens.EndToken;
import mp.tokens.MinusToken;
import mp.tokens.NumberToken;
import mp.tokens.PlusToken;
import mp.tokens.QuoteToken;
import mp.tokens.StartToken;
import mp.tokens.Token;
import mp.tokens.WordToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;

@StructurePattern("Bean Pattern")
@PropertyNames({"scannedString", "errors"})
@EditablePropertyNames({"scannedString"})
public class ATokenScanner implements TokenScanner{
	private String scannedString;
	private String errors = "";
	
	public void setScannedString(String scannedString){
		this.scannedString = " " + scannedString;
		this.errors = "";
	}
	
	@Override
	public boolean hasNext() {
		return scannedString.length() > 0;
	}

	@Override
	public Token next() {
		Token token = null;
		int tokenLength = 1;
		
		while(scannedString.length() > 0){
			char character = scannedString.charAt(0);
			if(character == '"'){
				tokenLength = getTokenLength('"', '}');
				String tokenString = scannedString.substring(1, tokenLength);
				token = new QuoteToken(tokenString);
			}else if(Character.isLetter(character)){
				tokenLength = getTokenLength(' ', '}');
				String tokenString = scannedString.substring(0, tokenLength);				
				token = getCommandToken(tokenString);
			}else if(Character.isDigit(character)){
				tokenLength = getTokenLength(' ', '}');
				String tokenString = scannedString.substring(0, tokenLength);
				token = new NumberToken(tokenString);
			}else if(character != ' '){
				tokenLength = 1;
				if(character == '{') {token = new StartToken(character + "");}
				if(character == '}') {token = new EndToken(character + "");}
				if(character == '+') {token = new PlusToken(character + "");}
				if(character == '-') {token = new MinusToken(character + "");}
			}else{
				int length = 1;
				if(token != null) {length = token.getInput().length();}
				scannedString = scannedString.substring(length, scannedString.length());
				continue;
			}
			
			scannedString = scannedString.substring(tokenLength, scannedString.length());
			break;
		}
		if(token == null) {return null;}
		return token;
	}
	
	private int getTokenLength(char endCharacter){
		int tokenLength = 1;
		while(scannedString.length() > tokenLength){
			if(scannedString.charAt(tokenLength) != endCharacter) {tokenLength++;}
			else {break;}
		}
		if(tokenLength == scannedString.length() && endCharacter == '"') {errors += "\n[ERROR - Missing closing quotation mark]";} 
		return tokenLength;
	}
	
	private int getTokenLength(char endCharacter, char endCharacter2){
		int length1 = getTokenLength(endCharacter);
		int length2 = getTokenLength(endCharacter2);
		if(length1 < length2){return length1;}
		return length2;
	}
	
	private Token getCommandToken(String command){
		if("call".equalsIgnoreCase(command)){return new CallCommand(command);}
		else if("define".equalsIgnoreCase(command)){return new DefineCommand(command);}
		else if("move".equalsIgnoreCase(command)){return new MoveCommand(command);}
		else if("proceedAll".equalsIgnoreCase(command)){return new ProceedAllCommand(command);}
		else if("redo".equalsIgnoreCase(command)){return new RedoCommand(command);}
		else if("repeat".equalsIgnoreCase(command)){return new RepeatCommand(command);}
		else if("rotateLeftArm".equalsIgnoreCase(command)){return new RotateLeftArmCommand(command);}
		else if("rotateRightArm".equalsIgnoreCase(command)){return new RotateRightArmCommand(command);}
		else if("say".equalsIgnoreCase(command)){return new SayCommand(command);}
		else if("sleep".equalsIgnoreCase(command)){return new SleepCommand(command);}
		else if("thread".equalsIgnoreCase(command)){return new ThreadCommand(command);}
		else if("undo".equalsIgnoreCase(command)){return new UndoCommand(command);}
		else if("wait".equalsIgnoreCase(command)){return new WaitCommand(command);}
		else if("approach".equalsIgnoreCase(command)){return new ApproachCommand(command);}
		else if("pass".equalsIgnoreCase(command)){return new PassCommand(command);}
		else if("fail".equalsIgnoreCase(command)){return new FailCommand(command);}
		return new WordToken(command);
	}

	@Override
	public String getScannedString() {
		return scannedString;
	}

	@Override
	public String getErrors() {
		return errors;
	}
	
}
