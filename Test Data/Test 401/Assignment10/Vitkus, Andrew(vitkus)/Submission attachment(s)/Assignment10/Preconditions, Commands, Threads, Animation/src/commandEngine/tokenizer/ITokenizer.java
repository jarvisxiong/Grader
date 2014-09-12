package commandEngine.tokenizer;

import java.util.Iterator;

import token.IToken;
import util.annotations.Tags;

@Tags({ "Scanner Bean" })
public interface ITokenizer extends Iterator<IToken> {
	public String getLine();

	public void setLine(String line);

	public ClearableTokenHistory getTokens();

	public String[] getErrors();
}
