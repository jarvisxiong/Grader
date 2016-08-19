package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"sleep"})
public class SleepCommandToken extends WordStoreToken implements CommandToken {

	public SleepCommandToken(String input) {
		super(input);
	}

}
