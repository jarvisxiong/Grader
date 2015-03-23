package token;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "End Token" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String" })
@EditablePropertyNames({ "String" })
public class EndToken extends Token implements IEndToken {

	public EndToken(String s) {
		super(s);
	}

}
