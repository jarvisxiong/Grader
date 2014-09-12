package token;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "Number Token" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String", "Number" })
@EditablePropertyNames({ "String" })
public class NumberToken extends Token implements INumberToken {

	public NumberToken(String s) {
		super(s);
		num = Integer.parseInt(s);
	}

	private int num;

	@Override
	public void setString(String s) {
		super.setString(s);
		num = Integer.parseInt(s);
	}

	@Override
	public int getNumber() {
		return num;
	}

}
