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
import grail.command.MoveCommandToken;
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
	
	public AScannerBean() {
		//fix null values in arrays
		largeTokenArray = new StoreToken[0];
		smallTokenArray = new StoreToken[0];
		tokenList = new ATokenHistory();
		errorString = new String[1];
		String defaultError = "No Errors Found!";
		errorString[0] = defaultError;
	}
	
	public void setScannedString(String input) {
		//initial values
		scannedString = input;
		arrayPosition = 0;
		errorPosition = 0;
		largeTokenArray = new StoreToken[input.length()];
		this.errorString = new String[input.length()];
		
		//populate array
		populateArray(scannedString);
		smallTokenArray = parseArray(largeTokenArray);
		errorString = errorCheck(errorString);
		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "ScannedString", null, this.scannedString));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Errors", null, this.errorString));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Tokens", null, this.smallTokenArray));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "TokenList", null, this.tokenList));

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
	
	
	private void populateArray(String input) {
		MutableIterator scan = new QuoteMutableIterator(input);
		while (scan.hasNext()) {
			
			String currentToken = scan.next();
            addTokenToHistory(currentToken);
            addTokenToArray(currentToken);
		}
		
	}
	
	private StoreToken[] parseArray(StoreToken[] largeArray) {
		int index = 0;
		int smallSize = getParsedArraySize(largeArray);
		StoreToken[] smallArray = new StoreToken[smallSize];
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
	
	private void addTokenToArray(String token) {
		int currentTokenLength = token.length();
			char currentChar = token.charAt(0);
			if ((currentChar == '+') && currentTokenLength == 1) {
				StoreToken output = new PlusStoreToken(token);
				largeTokenArray[arrayPosition] = output;
				
				arrayPosition++;
				
			} else if (currentChar == '-' && currentTokenLength == 1) {
				StoreToken output = new MinusStoreToken(token);
				largeTokenArray[arrayPosition] = output;
				
				arrayPosition++;
			} else if (currentChar == '"') {
				if ((token.charAt(currentTokenLength - 1) == '"') && currentTokenLength >1){
					token = processFinalToken(token);
					StoreToken output = new QuotedStoreToken(token);
					
					largeTokenArray[arrayPosition] = output;
					arrayPosition++;
					
				} else {
					errorString[errorPosition] = token + " was missing an end quote. Stored as a single word quote.";
					token = removeStartQuote(token);
					StoreToken output = new QuotedStoreToken(token);
					
					largeTokenArray[arrayPosition] = output;
					arrayPosition++;
					errorPosition++;
					
				}
			} else if (isNumber(token)) {
				NumberToken numClass = new NumberStoreToken(token);
				largeTokenArray[arrayPosition] = numClass;
				arrayPosition++;
				
			} else if (isWord(token)) {
				WordToken input = generateWordClass(token);
				largeTokenArray[arrayPosition] = input;
				arrayPosition++;
				
			} else if (currentChar == '{' && currentTokenLength == 1) {
				StoreToken output = new StartStoreToken(token);
				
				largeTokenArray[arrayPosition] = output;
				arrayPosition++;
				
			}else if (currentChar == '}' && currentTokenLength == 1) {
				StoreToken output = new EndStoreToken(token);
				
				largeTokenArray[arrayPosition] = output;
				arrayPosition++;
				
			}else {
				errorString[errorPosition] = token + " is an illegal Token. Token was ignored.";
				errorPosition++;
			}
		
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
	
	private String[] errorCheck(String[] input) {
		int currentSize = 0;
		for (int i = 0; i < input.length; i++) {
			if (!(input[i] == null)) {
				currentSize++;
			}
		}
		String[] ret;
		if (currentSize > 0) {
			ret = new String[currentSize];
			int index = 0;
			while (index < currentSize) {
				ret[index] = input[index];
				index++;
			}
		} else {
			ret = new String[1];
			ret[0] = "No Errors were Found!";
		}
		
		return ret;
	}

	public TokenHistory getTokenList() {
		return this.tokenList;
	}
	
	private void addTokenToHistory(String token) {
		int currentTokenLength = token.length();
		char currentChar = token.charAt(0);
		if ((currentChar == '+') && currentTokenLength == 1) {
			StoreToken output = new PlusStoreToken(token);
			tokenList.add(output);
			
		} else if (currentChar == '-' && currentTokenLength == 1) {
			StoreToken output = new MinusStoreToken(token);
			tokenList.add(output);
		} else if (currentChar == '"') {
			if ((token.charAt(currentTokenLength - 1) == '"') && currentTokenLength >1){
				token = processFinalToken(token);
				StoreToken output = new QuotedStoreToken(token);
				tokenList.add(output);
				
			} else {
				token = removeStartQuote(token);
				StoreToken output = new QuotedStoreToken(token);
				tokenList.add(output);
				
			}
		} else if (isNumber(token)) {
			NumberToken numClass = new NumberStoreToken(token);
			tokenList.add(numClass);
			
		} else if (isWord(token)) {
			WordToken input = generateWordClass(token);
			tokenList.add(input);
			
		} else if (currentChar == '{' && currentTokenLength == 1) {
			StoreToken output = new StartStoreToken(token);
			tokenList.add(output);
			
		}else if (currentChar == '}' && currentTokenLength == 1) {
			StoreToken output = new EndStoreToken(token);
			tokenList.add(output);
			
		}else {
			
		}
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyListenerSupport.addElement(arg0);
		
	}
	
}
