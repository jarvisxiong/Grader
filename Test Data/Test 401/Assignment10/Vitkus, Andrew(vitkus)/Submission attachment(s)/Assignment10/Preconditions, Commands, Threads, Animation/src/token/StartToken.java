package token;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "Start Token" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String" })
@EditablePropertyNames({ "String" })
public class StartToken extends Token implements IStartToken {

	public StartToken(String s) {
		super(s);
	}

}
