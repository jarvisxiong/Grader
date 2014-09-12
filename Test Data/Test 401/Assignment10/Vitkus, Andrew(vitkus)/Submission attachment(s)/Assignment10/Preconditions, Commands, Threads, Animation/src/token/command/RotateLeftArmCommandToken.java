package token.command;

import token.WordToken;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "rotateLeftArm" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String", "Word" })
@EditablePropertyNames({ "String" })
public class RotateLeftArmCommandToken extends WordToken implements
		IRotateLeftArmCommandToken {

	public RotateLeftArmCommandToken(String s) {
		super(s);
	}

}
