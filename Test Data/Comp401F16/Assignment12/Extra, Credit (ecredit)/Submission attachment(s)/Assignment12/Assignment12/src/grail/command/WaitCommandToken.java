package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"wait"})
public class WaitCommandToken extends WordStoreToken implements CommandToken {

	public WaitCommandToken(String input) {
		super(input);
		
	}

}
