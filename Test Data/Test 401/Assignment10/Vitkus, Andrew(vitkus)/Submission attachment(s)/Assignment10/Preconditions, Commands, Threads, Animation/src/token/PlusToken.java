package token;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "Plus Token" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String" })
@EditablePropertyNames({ "String" })
public class PlusToken extends Token implements IPlusToken {

	public PlusToken(String s) {
		super(s);
	}

}
