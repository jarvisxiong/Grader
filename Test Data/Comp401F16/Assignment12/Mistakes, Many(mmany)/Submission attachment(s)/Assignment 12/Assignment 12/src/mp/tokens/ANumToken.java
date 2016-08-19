package mp.tokens;

import mp.tokens.Token;
import mp.tokens.NumValueInt;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Number"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class ANumToken extends ABasicToken implements NumValueInt {

	public ANumToken(String initInput) {
		super(initInput);
	}

	public int getValue(){
		int numValue = Integer.parseInt(super.getInput());
		return numValue;
	}
}	
