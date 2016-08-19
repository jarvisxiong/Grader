package grail.tokens;

import grail.interfaces.NumberToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.StructurePatternNames;

@Tags({"Number"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input", "Value"}) 
@EditablePropertyNames({"Input"})

public class NumberStoreToken extends BasicStoreToken implements NumberToken {

	public NumberStoreToken(String input) {
		super(input);
	}

	public int getValue() {
		return Integer.parseInt(getInput());
	}

}
