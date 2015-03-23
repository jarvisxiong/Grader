package token;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "Token" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String" })
@EditablePropertyNames({ "String" })
public abstract class Token implements IToken {

	private String s;

	public Token(String s) {
		setString(s);
	}

	@Override
	public void setString(String s) {
		this.s = s;
	}

	@Override
	public String getString() {
		return s;
	}

}
