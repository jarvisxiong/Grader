package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"call"})
public class CallCommandToken extends WordStoreToken implements CommandToken  {

	public CallCommandToken(String input) {
		super(input);
		
	}

}
