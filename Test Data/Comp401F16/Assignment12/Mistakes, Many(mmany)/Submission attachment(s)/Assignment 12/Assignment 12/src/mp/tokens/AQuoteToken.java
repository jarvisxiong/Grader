package mp.tokens;

import mp.tokens.Token;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Quote"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})

public class AQuoteToken  extends ABasicToken implements Token {

	public AQuoteToken(String initInput) {
		super(initInput);
	}
}

