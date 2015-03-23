package token;

import util.annotations.Tags;

@Tags({ "Word Token" })
public interface IWordToken extends IToken {
	public String getWord();
}
