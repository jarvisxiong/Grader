package grail.command;
import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"undo"})
public class UndoCommandToken extends WordStoreToken implements CommandToken {

	public UndoCommandToken(String input) {
		super(input);
	}

}
