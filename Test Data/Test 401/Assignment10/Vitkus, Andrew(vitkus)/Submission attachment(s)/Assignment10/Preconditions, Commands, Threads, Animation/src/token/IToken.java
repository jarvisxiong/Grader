package token;

import util.annotations.Tags;

@Tags({ "Token" })
public interface IToken {
	public void setString(String s);

	public String getString();
}
