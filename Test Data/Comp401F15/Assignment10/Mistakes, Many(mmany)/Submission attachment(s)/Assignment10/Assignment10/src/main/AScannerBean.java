package main;


import java.util.Iterator;

import mp.tokens.ATokenHistory;
import mp.tokens.Token;
import mp.tokens.TokenHistory;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Errors", "ScannedString", "TokenList"})
@EditablePropertyNames({"ScannedString"})

public class AScannerBean implements ScannerBean{
	private final int largeArraySize = 100;
	
	private String scannedString;
	private Iterator<Token> scanner;
	
	private TokenHistory tokenList;
	
	public AScannerBean(){
		scanner = new ATokenScanner();
		tokenList = new ATokenHistory();
	}
	
	private void printLine(){
		tokenList.clear();
		((ATokenScanner) scanner).setScannedString(scannedString);
		while(scanner.hasNext()){
			Token token = scanner.next();
			if(token == null){break;}
			tokenList.add(token);
		}
	}
	
	@Override
	public String getErrors(){
		return ((ATokenScanner) scanner).getErrors();
	}
	
	@Override
	public String getScannedString() {
		return scannedString;
	}

	@Override
	public void setScannedString(String scannedString) {
		this.scannedString = scannedString;
		printLine();
	}

	@Override
	public TokenHistory getTokenList() {
		return tokenList;
	}

}
