package grail.helpers;

import grail.interfaces.MutableIterator;

public class QuoteMutableIterator implements MutableIterator{
	private int currentIndexPosition = 0;
	private String inputString = "";
	
	
	public QuoteMutableIterator(String input) {
		inputString = input;
	}

	@Override
	public boolean hasNext() {
		char currentChar = 'a';
		
		//probes for another token. Returns false if it does not find one.
		int currentPosition = currentIndexPosition;
		int stringLength = inputString.length();
		if ((currentPosition < stringLength)) {
			currentChar = inputString.charAt(currentPosition);
		}	
		while ((currentChar == ' ') && currentPosition < stringLength) {
			currentPosition++;
			if ((currentPosition < stringLength)) {
				currentChar = inputString.charAt(currentPosition);
			} else {
				break;
			}
		}
		
		if (currentPosition < (stringLength)) {
			return true;
		} 
			return false;
	}

	@Override
	public String next() {
		String currentReturn = "";
		
		char currentChar = inputString.charAt(currentIndexPosition);
		if ((currentChar == ' ')){
			clearWhiteSpace();
			if (currentIndexPosition < inputString.length()){
				currentChar = inputString.charAt(currentIndexPosition);
			}
			if (currentChar == '"') {
				currentReturn = processQuote();
			} else {
				currentReturn = createNextToken(currentReturn, currentChar);
			}
		} else {
			if (currentIndexPosition < inputString.length()){
				currentChar = inputString.charAt(currentIndexPosition);
			}
			
			if (currentChar == '"') {
				currentReturn = processQuote();
			} else {
				currentReturn = createNextToken(currentReturn, currentChar);
			}

		}
		
		return currentReturn;
	}

	
	@Override
	public void setString(String input) {
		this.inputString = input;
		
	}

	@Override
	public void setCurrentPosition(int position) {
		currentIndexPosition = position;
		
	}

	@Override
	public int getCurrentPosition() {
		return currentIndexPosition;
	}

	@Override
	public String getString() {
		return inputString;
	}

	
	private void clearWhiteSpace() {
		char currentChar = 'a';
		int stringLength = inputString.length();
		if ((currentIndexPosition < stringLength)) {
			currentChar = inputString.charAt(currentIndexPosition);
		}	
		while ((currentChar == ' ') && currentIndexPosition < stringLength) {
			currentIndexPosition++;
			if (!(currentIndexPosition >= stringLength)) {
				currentChar = inputString.charAt(currentIndexPosition);
			} else {
				break;
			}
		}
	}
	
	private String createNextToken(String currentReturn, char currentChar) {
		int stringLength = inputString.length();
		while(!(currentChar == ' ') && currentIndexPosition < stringLength) {
			currentReturn = currentReturn + currentChar;
			currentIndexPosition++;
				if (currentIndexPosition < inputString.length()){
				currentChar = inputString.charAt(currentIndexPosition);
				}
		}
		
		return currentReturn;
	}
	
	private String processQuote() {
		String returnString = "";
		int tempIndex = currentIndexPosition;
		int tempIndexQuote = currentIndexPosition;
		String firstToken = "";
		char currentChar = inputString.charAt(tempIndex);
		returnString = returnString + currentChar;
		
		//makes the first token without a space in case one is not found.
		while(!(currentChar == ' ') && tempIndex < inputString.length()) {
			firstToken = firstToken + currentChar;
			tempIndex++;
				if (tempIndex < inputString.length()){
				currentChar = inputString.charAt(tempIndex);
				}
		}
		
		tempIndexQuote++;
		if ((tempIndexQuote < inputString.length())) {
			currentChar = inputString.charAt(tempIndexQuote);
		} else {
			currentChar = inputString.charAt(currentIndexPosition);
		}
		
		while(!(currentChar == '"') && tempIndexQuote < inputString.length()) {
			returnString = returnString + currentChar;
			tempIndexQuote++;
				if (tempIndexQuote < inputString.length()){
				currentChar = inputString.charAt(tempIndexQuote);
				}
		}
		
		if (currentChar == '"' && !(tempIndexQuote == inputString.length())) {
			returnString = returnString + "\"";
			tempIndexQuote++;
			currentIndexPosition = tempIndexQuote;
			return returnString;
		} else {
			currentIndexPosition = tempIndex;
			return firstToken;
		}
		
	}
}
