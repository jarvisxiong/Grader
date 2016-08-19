package mp.tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ClearableHistory"})
@StructurePattern(StructurePatternNames.LIST_PATTERN)
public class ATokenHistory implements TokenHistory{
	private static final int MAX_SIZE = 50;
	private Token[] contents = new Token[MAX_SIZE];
	private int size = 0;
	
	@Override
	public void add(Token element) {
		if(size != MAX_SIZE){
			contents[size] = element;
			size++;
		}
	}

	@Override
	public Token get(int index) {
		return contents[index];
	}

	@Override
	public int size() {
		return size;
	}
	
	@Tags({"clear"})
	@Override
	public void clear() {
		for(int i = 0 ; i < MAX_SIZE; i++){
			contents[i] = null;
		}
		size = 0;
	}
	
}
