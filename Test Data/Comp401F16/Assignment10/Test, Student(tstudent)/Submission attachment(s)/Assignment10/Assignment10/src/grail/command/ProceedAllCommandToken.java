package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"proceedAll"})
public class ProceedAllCommandToken extends WordStoreToken implements CommandToken  {

	public ProceedAllCommandToken(String input) {
		super(input);
	}

}
