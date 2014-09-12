package commandEngine.tokenizer;

import token.IToken;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.VECTOR_PATTERN)
public class TokenHistory implements ITokenHistory {
	public static final int MAX_SIZE = 50;
	protected IToken[] contents;
	protected int size;

	public TokenHistory() {
		contents = new IToken[MAX_SIZE];
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public IToken tokenAt(int index) {
		if (index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		}
		return contents[index];
	}

	@Override
	public boolean isFull() {
		return size == MAX_SIZE;

	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void addToken(IToken token) {
		if (isFull())
			System.out.println("Adding token to a full history\n");
		else {
			contents[size] = token;
			size++;
		}
	}
}
