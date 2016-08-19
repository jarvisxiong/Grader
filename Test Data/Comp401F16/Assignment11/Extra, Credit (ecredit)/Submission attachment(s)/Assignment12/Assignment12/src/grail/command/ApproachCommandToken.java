package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"approach"})
public class ApproachCommandToken extends WordStoreToken implements CommandToken {

	public ApproachCommandToken(String input) {
		super(input);
	}

}
