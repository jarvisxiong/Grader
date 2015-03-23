package token.command;

import token.WordToken;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "move" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String", "Word" })
@EditablePropertyNames({ "String" })
public class MoveCommandToken extends WordToken implements IMoveCommandToken {

	public MoveCommandToken(String s) {
		super(s);
	}
}
