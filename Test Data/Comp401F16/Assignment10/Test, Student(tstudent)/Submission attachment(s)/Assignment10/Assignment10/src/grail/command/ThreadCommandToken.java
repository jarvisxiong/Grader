package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"thread"})
public class ThreadCommandToken extends WordStoreToken implements CommandToken {

	public ThreadCommandToken(String input) {
		super(input);
	}

}
