package grail.command;

import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"move"})

public class MoveCommandToken extends WordStoreToken implements CommandToken {

	public MoveCommandToken(String input) {
		super(input);
	}

}
