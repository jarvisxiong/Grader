package grail.tokens;

import util.annotations.Tags;
import grail.interfaces.StoreToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Minus"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class MinusStoreToken extends BasicStoreToken implements StoreToken {
		
	public MinusStoreToken(String input) {
		super(input);
	}

}
