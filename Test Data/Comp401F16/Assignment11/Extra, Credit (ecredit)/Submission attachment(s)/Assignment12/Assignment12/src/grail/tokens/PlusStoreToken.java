package grail.tokens;

import grail.interfaces.StoreToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.StructurePatternNames;

@Tags({"Plus"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class PlusStoreToken extends BasicStoreToken implements StoreToken{

	public PlusStoreToken(String input) {
		super(input);
	}

}
