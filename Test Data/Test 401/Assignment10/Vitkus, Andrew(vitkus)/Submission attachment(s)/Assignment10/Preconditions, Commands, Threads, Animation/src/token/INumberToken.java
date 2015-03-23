package token;

import util.annotations.Tags;

@Tags({ "Number Token" })
public interface INumberToken extends IToken {
	public int getNumber();
}
