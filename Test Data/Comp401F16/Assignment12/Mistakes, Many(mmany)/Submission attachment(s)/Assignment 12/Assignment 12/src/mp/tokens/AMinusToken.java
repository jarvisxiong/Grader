package mp.tokens;

import mp.tokens.Token;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Plus"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})

public class AMinusToken extends ABasicToken implements Token {
	public AMinusToken(String initInput) {
		super(initInput);
	}

}
