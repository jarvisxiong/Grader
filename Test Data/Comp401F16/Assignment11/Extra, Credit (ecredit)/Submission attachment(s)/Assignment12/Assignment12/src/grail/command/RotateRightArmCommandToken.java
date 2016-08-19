package grail.command;

import grail.interfaces.CommandToken;
import grail.tokens.WordStoreToken;
import util.annotations.Tags;

@Tags({"rotateRightArm"})
public class RotateRightArmCommandToken extends WordStoreToken implements CommandToken {

	public RotateRightArmCommandToken(String input) {
		super(input);
	}

}
