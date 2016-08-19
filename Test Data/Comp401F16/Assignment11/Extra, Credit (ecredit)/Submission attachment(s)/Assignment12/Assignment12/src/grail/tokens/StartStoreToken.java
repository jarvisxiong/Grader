package grail.tokens;

import util.annotations.Tags;
import grail.interfaces.StoreToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Start"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class StartStoreToken extends BasicStoreToken implements StoreToken {

	public StartStoreToken(String input) {
		super(input);
	}

}
