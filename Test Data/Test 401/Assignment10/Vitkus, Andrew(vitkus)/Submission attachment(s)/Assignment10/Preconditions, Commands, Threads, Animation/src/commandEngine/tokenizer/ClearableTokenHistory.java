package commandEngine.tokenizer;

import token.IToken;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({ "Clearable History" })
@StructurePattern(StructurePatternNames.VECTOR_PATTERN)
public class ClearableTokenHistory extends TokenHistory implements
		IClearableTokenHistory {

	public ClearableTokenHistory() {
		super();
	}

	@Override
	public void clear() {
		contents = new IToken[MAX_SIZE];
		size = 0;
	}

}
