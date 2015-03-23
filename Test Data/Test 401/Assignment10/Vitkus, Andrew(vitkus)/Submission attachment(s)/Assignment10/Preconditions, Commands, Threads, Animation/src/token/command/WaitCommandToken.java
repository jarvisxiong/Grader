package token.command;

import token.WordToken;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "wait" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String", "Word" })
@EditablePropertyNames({ "String" })
public class WaitCommandToken extends WordToken implements IWaitCommandToken {

	public WaitCommandToken(String s) {
		super(s);
	}

}
