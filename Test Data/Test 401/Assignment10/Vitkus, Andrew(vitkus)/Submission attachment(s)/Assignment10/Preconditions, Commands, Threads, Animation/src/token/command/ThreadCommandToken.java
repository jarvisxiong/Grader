package token.command;

import token.WordToken;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "thread" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String", "Word" })
@EditablePropertyNames({ "String" })
public class ThreadCommandToken extends WordToken implements
		IThreadCommandToken {

	public ThreadCommandToken(String s) {
		super(s);
	}

}
