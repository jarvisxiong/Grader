package mp.tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ClearableHistory"})
@StructurePattern(StructurePatternNames.LIST_PATTERN)
public interface TokenHistory {
	public void add(Token element);
	public Token get (int index); 
	public int size();
	@Tags({"clear"})
	public void clear();
}
