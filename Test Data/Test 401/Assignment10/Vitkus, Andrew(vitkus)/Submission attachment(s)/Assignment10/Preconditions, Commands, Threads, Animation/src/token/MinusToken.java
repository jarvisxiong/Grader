package token;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "Minus Token" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String" })
@EditablePropertyNames({ "String" })
public class MinusToken extends Token implements IMinusToken {

	public MinusToken(String s) {
		super(s);
	}

}
