package commandEngine.tokenizer;

import token.IToken;

public interface ITokenHistory {
	public void addToken(IToken token);

	public IToken tokenAt(int index);

	public boolean isFull();

	public boolean isEmpty();

	public int size();
}
