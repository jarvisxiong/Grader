package grail.command;

import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"rotateLeftArm"})
public class RotateLeftArmCommandToken extends WordStoreToken implements CommandToken {

	public RotateLeftArmCommandToken(String input) {
		super(input);
	}

}
