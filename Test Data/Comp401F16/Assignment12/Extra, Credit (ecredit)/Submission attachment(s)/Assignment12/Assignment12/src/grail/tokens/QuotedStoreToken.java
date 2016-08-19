package grail.tokens;

import util.annotations.Tags;
import grail.interfaces.StoreToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Quote"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})
public class QuotedStoreToken extends BasicStoreToken implements StoreToken{

	public QuotedStoreToken(String input) {
		super(input);
	}

}
