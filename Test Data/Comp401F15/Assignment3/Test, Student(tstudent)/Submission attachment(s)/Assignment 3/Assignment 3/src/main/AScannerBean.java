package main;


import java.util.Iterator;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ScannerBean"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"ScannedString"})
@EditablePropertyNames({"ScannedString"})

public class AScannerBean implements ScannerBean{
	private String scannedString;
	private Iterator<Token> scanner;
	
	public AScannerBean(){
		scanner = new ATokenScanner();
	}
	
	private void printLine(){
		((ATokenScanner) scanner).setScannedString(scannedString);
		while(scanner.hasNext()){
			Token token = scanner.next();
			if(token == null){break;}
			System.out.println(token);
			System.out.println(token.getInput());
			if(token instanceof IntValueToken){System.out.println(((IntValueToken) token).getValue());}
			if(token instanceof StringValueToken){System.out.println(((StringValueToken) token).getValue());}
		}
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

}
