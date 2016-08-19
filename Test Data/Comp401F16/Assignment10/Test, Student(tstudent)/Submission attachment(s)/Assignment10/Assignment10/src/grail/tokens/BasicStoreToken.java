package grail.tokens;

import grail.interfaces.StoreToken;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Basic"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class BasicStoreToken implements StoreToken {
	private String storedString = "";
	
	public BasicStoreToken(String input) {
		storedString = input;
	}
	
	public void setInput(String input) {
		storedString = input;
	}
	
	public String getInput() {
		return storedString;
	}
}
