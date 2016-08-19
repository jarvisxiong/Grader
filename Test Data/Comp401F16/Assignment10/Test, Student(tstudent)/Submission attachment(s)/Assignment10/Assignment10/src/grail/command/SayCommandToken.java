package grail.command;

import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"say"})

public class SayCommandToken extends WordStoreToken implements CommandToken {

	public SayCommandToken(String input) {
		super(input);
	}

}
