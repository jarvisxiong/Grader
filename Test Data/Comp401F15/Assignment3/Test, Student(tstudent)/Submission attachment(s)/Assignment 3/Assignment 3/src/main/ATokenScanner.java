package main;

public class ATokenScanner implements TokenScanner{
	private String scannedString;
	
	public void setScannedString(String scannedString){
		this.scannedString = " " + scannedString;
	}
	
	@Override
	public boolean hasNext() {
		return scannedString.length() > 0;
	}

	@Override
	public Token next() {
		Token token = null;
		int tokenLength = 1;
		
		while(scannedString.length() > 1){
			char character = scannedString.charAt(1);
			if(character == '"'){
				tokenLength = getTokenLength('"');
				String tokenString = scannedString.substring(2, tokenLength);
				token = new QuoteToken(tokenString);
			}else if(Character.isLetter(character)){
				tokenLength = getTokenLength(' ');
				String tokenString = scannedString.substring(1, tokenLength);
				token = new WordToken(tokenString);
			}else if(Character.isDigit(character)){
				tokenLength = getTokenLength(' ');
				String tokenString = scannedString.substring(1, tokenLength);
				token = new NumberToken(tokenString);
			}else if(character == ' '){
				int length = 1;
				if(token != null) {length = token.getInput().length();}
				scannedString = scannedString.substring(length, scannedString.length());
				continue;
			}else{
				tokenLength = 1;
				if(character == '{') {token = new StartToken(character + "");}
				if(character == '}') {token = new EndToken(character + "");}
				if(character == '+') {token = new PlusToken(character + "");}
				if(character == '-') {token = new MinusToken(character + "");}
			}
			
			scannedString = scannedString.substring(tokenLength, scannedString.length());
			break;
		}
		if(token == null) {return null;}
		return token;
	}
	
	private int getTokenLength(char endCharacter){
		int tokenLength = 2;
		while(scannedString.length() > tokenLength){
			if(scannedString.charAt(tokenLength) != endCharacter) {tokenLength++;}
			else {break;}
		}
		if(tokenLength == scannedString.length() && endCharacter == '"') {System.out.println("[ERROR - Missing closing quotation mark]");} 
		return tokenLength;
	}

	@Override
	public String getScannedString() {
		return scannedString;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
}
