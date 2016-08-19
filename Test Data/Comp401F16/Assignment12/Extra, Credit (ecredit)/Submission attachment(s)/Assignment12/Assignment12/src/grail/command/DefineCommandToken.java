package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"define"})
public class DefineCommandToken extends WordStoreToken implements CommandToken  {

	public DefineCommandToken(String input) {
		super(input);
	}

}
