package grail.command;

import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"fail"})
public class FailCommandToken extends WordStoreToken implements CommandToken {

	public FailCommandToken(String input) {
		super(input);
	}

}
