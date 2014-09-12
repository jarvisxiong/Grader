package token;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({ "Word Token" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "String", "Word" })
@EditablePropertyNames({ "String" })
public class WordToken extends Token implements IWordToken {

	public WordToken(String s) {
		super(s);
		word = s.toLowerCase();
	}

	private String word;

	/*
	 * @Override public void setString(String s) { super.setString(s); word =
	 * s.toLowerCase(); }
	 */

	@Override
	public String getWord() {
		return word;
	}

}
