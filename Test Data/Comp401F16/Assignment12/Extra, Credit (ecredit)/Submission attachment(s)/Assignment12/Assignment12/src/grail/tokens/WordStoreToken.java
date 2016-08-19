package grail.tokens;

import grail.interfaces.WordToken;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Word"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input","Value"}) 
@EditablePropertyNames({"Input"})

public class WordStoreToken extends BasicStoreToken implements WordToken{

	public WordStoreToken(String input) {
		super(input);
	}

	public String getValue() {
		return getInput().toLowerCase();
	}

	
}
