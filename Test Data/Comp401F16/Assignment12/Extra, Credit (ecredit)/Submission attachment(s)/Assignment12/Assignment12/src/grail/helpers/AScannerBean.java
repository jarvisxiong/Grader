package grail.helpers;

import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern; 
import util.annotations.StructurePatternNames; 
import util.annotations.Tags;
import grail.interfaces.WordToken;
import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;
import grail.tokens.EndStoreToken;
import grail.tokens.MinusStoreToken;
import grail.tokens.NumberStoreToken;
import grail.tokens.PlusStoreToken;
import grail.tokens.QuotedStoreToken;
import grail.tokens.StartStoreToken;
import grail.tokens.WordStoreToken;
import grail.interfaces.NumberToken;
import grail.interfaces.StoreToken;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.command.ApproachCommandToken;
import grail.command.CallCommandToken;
import grail.command.DefineCommandToken;
import grail.command.FailCommandToken;
import grail.command.MoveCommandToken;
import grail.command.PassCommandToken;
import grail.command.PrintStoredCommandToken;
import grail.command.ProceedAllCommandToken;
import grail.command.RedoCommandToken;
import grail.command.RepeatCommandToken;
import grail.command.RotateLeftArmCommandToken;
import grail.command.RotateRightArmCommandToken;
import grail.command.SayCommandToken;
import grail.command.SleepCommandToken;
import grail.command.ThreadCommandToken;
import grail.command.UndoCommandToken;
import grail.command.WaitCommandToken;
import grail.exceptions.ScanningException;
import grail.interfaces.ScannerBean;
import grail.interfaces.MutableIterator;

@Tags({"ScannerBean"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"ScannedString", "Errors" , "Tokens", "TokenList"}) 
@EditablePropertyNames({"ScannedString"})

public class AScannerBean implements ScannerBean {
	private String scannedString = ""; 
	private StoreToken[] largeTokenArray;
	private StoreToken[] smallTokenArray;
	private int arrayPosition = 0;
	private int errorPosition = 0;
	private String[] errorString;
	private TokenHistory tokenList;
	private static final int COMPONENT_WIDTH = 600;
	protected PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	private String errorsAsString;
	
	public AScannerBean() {
		//fix null values in arrays
		largeTokenArray = new StoreToken[0];
		smallTokenArray = new StoreToken[0];
		tokenList = new ATokenHistory();
		errorString = new String[1];
		String defaultError = "No Errors Found!";
		errorString[0] = defaultError;
		errorsAsString = "";
	}
	
	public void setScannedString(String input) throws ScanningException{
		//initial values
		scannedString = input;
		arrayPosition = 0;
		errorPosition = 0;
		largeTokenArray = new StoreToken[input.length()];
		this.errorString = new String[input.length()];
		this.errorsAsString = "Scanning returned the following errors:  \n";
		boolean errorsDetected = false;
		//populate array
		MutableIterator scan = new QuoteMutableIterator(input);
		while (scan.hasNext()) {
			
			String currentToken = scan.next();
            try {
            	StoreToken toAddToArray = getStoreToken(currentToken);
            	StoreToken toAddToHistory = getStoreToken(currentToken);
            	largeTokenArray[this.arrayPosition] = toAddToArray;
            	this.tokenList.add(toAddToHistory);
            	arrayPosition++;
            } catch (ScanningException se){
            	errorsDetected = true;
            	this.errorString[errorPosition] = se.getLocalizedMessage();
            	this.errorsAsString = this.errorsAsString + se.getLocalizedMessage() + "\n";
            	errorPosition++;
            }
		}
		
		smallTokenArray = parseStoreTokenArray(largeTokenArray);
		this.errorString = parseStringArray(this.errorString);
		
		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "ScannedString", null, this.scannedString));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Errors", null, this.errorString));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Tokens", null, this.smallTokenArray));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "TokenList", null, this.tokenList));

		}
		
		if (errorsDetected) {
			throw new ScanningException(this.errorsAsString);
		}
		
	}
	
	@ComponentWidth (COMPONENT_WIDTH)
	public String getScannedString() {
		return scannedString;
	}
	
	public StoreToken[] getTokens() {
		return this.smallTokenArray;
	}
	
	public String[] getErrors() {
		return this.errorString;
	}
	
	
	private StoreToken[] parseStoreTokenArray(StoreToken[] largeArray) {
		int index = 0;
		int smallSize = getParsedArraySize(largeArray);
		StoreToken[] smallArray = new StoreToken[smallSize];
		while (index < smallSize) {
			smallArray[index] = largeArray[index];
			index++;
		}
		
		return smallArray;
	}
	
	private String[] parseStringArray(String[] largeArray) {
		int index = 0;
		int smallSize = getParsedArraySize(largeArray);
		String[] smallArray = new String[smallSize];
		while (index < smallSize) {
			smallArray[index] = largeArray[index];
			index++;
		}
		
		return smallArray;
	}
	
	private int getParsedArraySize(StoreToken[] input) {
		int currentSize = 0;
		for (int i = 0; i < input.length; i++) {
			if (!(input[i] == null)) {
				currentSize++;
			}
		}
		
		return currentSize;
	}
	
	private int getParsedArraySize(String[] input) {
		int currentSize = 0;
		for (int i = 0; i < input.length; i++) {
			if (!(input[i] == null)) {
				currentSize++;
			}
		}
		
		return currentSize;
	}
	
	private StoreToken getStoreToken(String token) throws ScanningException {
		int currentTokenLength = token.length();
		char currentChar = token.charAt(0);
		if ((currentChar == '+') && currentTokenLength == 1) {
			StoreToken output = new PlusStoreToken(token);
			return output;
			
		} else if (currentChar == '-' && currentTokenLength == 1) {
			StoreToken output = new MinusStoreToken(token);
			return output;
		} else if (currentChar == '"') {
			if ((token.charAt(currentTokenLength - 1) == '"') && currentTokenLength >1){
				token = processFinalToken(token);
				StoreToken output = new QuotedStoreToken(token);
				return output;
				
			} else {
				//errorString[errorPosition] = token + " was missing an end quote. Stored as a single word quote.";
				token = removeStartQuote(token);
				StoreToken output = new QuotedStoreToken(token);
				return output;
				
			}
		} else if (isNumber(token)) {
			NumberToken numClass = new NumberStoreToken(token);
			return numClass;
			
		} else if (isWord(token)) {
			WordToken input = generateWordClass(token);
			return input;
			
		} else if (currentChar == '{' && currentTokenLength == 1) {
			StoreToken output = new StartStoreToken(token);
			return output;
			
		}else if (currentChar == '}' && currentTokenLength == 1) {
			StoreToken output = new EndStoreToken(token);
			return output;
			
		}else {
			throw new ScanningException("The string entered: " + token + " is not an acceptable token! Token Ignored.");
		}
	}

	public TokenHistory getTokenList() {
		return this.tokenList;
	}
	
	private WordToken generateWordClass(String input) {
		WordToken ret;
		if ("move".equalsIgnoreCase(input)) {
			ret = new MoveCommandToken(input);
		} else if ("say".equalsIgnoreCase(input)){
			ret = new SayCommandToken(input);
		} else if ("rotateleftarm".equalsIgnoreCase(input)){
			ret = new RotateLeftArmCommandToken(input);
		} else if ("rotaterightarm".equalsIgnoreCase(input)){
			ret = new RotateRightArmCommandToken(input);
		} else if ("repeat".equalsIgnoreCase(input)){
			ret = new RepeatCommandToken(input);
		} else if ("define".equalsIgnoreCase(input)){
			ret = new DefineCommandToken(input);
		} else if ("call".equalsIgnoreCase(input)){
			ret = new CallCommandToken(input);
		} else if ("thread".equalsIgnoreCase(input)){
			ret = new ThreadCommandToken(input);
		} else if ("wait".equalsIgnoreCase(input)){
			ret = new WaitCommandToken(input);
		} else if ("proceedall".equalsIgnoreCase(input)){
			ret = new ProceedAllCommandToken(input);
		} else if ("sleep".equalsIgnoreCase(input)){
			ret = new SleepCommandToken(input);
		} else if ("undo".equalsIgnoreCase(input)){
			ret = new UndoCommandToken(input);
		} else if ("redo".equalsIgnoreCase(input)){
			ret = new RedoCommandToken(input);
		} else if ("approach".equalsIgnoreCase(input)) {
			ret = new ApproachCommandToken(input);
		} else if ("pass".equalsIgnoreCase(input)) {
			ret = new PassCommandToken(input);
		} else if ("fail".equalsIgnoreCase(input)) {
			ret = new FailCommandToken(input);
		}else if ( "print".equalsIgnoreCase(input)) {
			ret = new PrintStoredCommandToken(input);
		} else {
			ret = new WordStoreToken(input);
		}
		return ret;
	}
	
/*	private static boolean isLetter(char inputChar) {
		return ((inputChar >= 'a' && inputChar <= 'z') || (inputChar >= 'A' && inputChar <= 'Z'));
	} */
	
	private boolean checkForStartQuote(String token) {
		if (token.length() > 0 && token.charAt(0) == '"') {
			return true;
		} 
		return false;
		
	}
	
	private boolean checkForEndQuote(String token) {
		if (token.length() > 0  && token.charAt(token.length() - 1) == '"') {
			return true;
		}
		
		return false;
	}
	
	private String removeStartQuote(String token) {
		String returnString = "";
		int index = 0;
		while (index < token.length() - 1) {
			returnString = returnString + token.charAt(index + 1);
			index++;
		}
		
		return returnString;
	} 
	
	private boolean isWord(String token) {
		int index = 0;
		while (index < token.length()) {
			if (!Character.isLetter(token.charAt(index))) {
				return false;
			}
			index++;
		}
		
		return true;
	}
	
	private boolean isNumber(String token){
		int index = 0;
		while (index < token.length()) {
			if (!Character.isDigit(token.charAt(index))) {
				return false;
			}
			index++;
		}
		
		return true;
	}
	
	
	private String processFinalToken(String token) {
		String returnString = token;
		
		if (checkForStartQuote(token) && checkForEndQuote(token)) {
			returnString = returnString.substring(1, token.length() - 1);
		}
		
		return returnString;
	}
	
	

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyListenerSupport.addElement(arg0);
		
	}
	
}
