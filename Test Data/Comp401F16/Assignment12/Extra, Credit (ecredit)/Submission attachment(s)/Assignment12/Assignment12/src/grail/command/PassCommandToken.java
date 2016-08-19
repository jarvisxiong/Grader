package grail.command;

import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"pass"})
public class PassCommandToken extends WordStoreToken implements CommandToken {

	public PassCommandToken(String input) {
		super(input);
	}

}
