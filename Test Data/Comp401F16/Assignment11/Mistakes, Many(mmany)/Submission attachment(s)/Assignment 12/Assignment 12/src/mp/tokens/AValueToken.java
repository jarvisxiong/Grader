package mp.tokens;

import mp.tokens.Token;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public abstract class AValueToken extends ABasicToken implements WordValueInt {
	
	public AValueToken(String initInput) {
		super(initInput);
	}

	public String getValue(){
		String wordValue = super.getInput().toLowerCase();
		return wordValue;
	}
}	