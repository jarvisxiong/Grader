package grail.command;

import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"repeat"})
public class RepeatCommandToken extends WordStoreToken implements CommandToken  {

	public RepeatCommandToken(String input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

}
