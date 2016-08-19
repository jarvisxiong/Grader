package grail.command;

import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"print"})
public class PrintStoredCommandToken extends WordStoreToken implements CommandToken {

	public PrintStoredCommandToken(String input) {
		super(input);
	}

}
