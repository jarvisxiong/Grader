package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"redo"})
public class RedoCommandToken extends WordStoreToken implements CommandToken {

	public RedoCommandToken(String input) {
		super(input);
	}

}
