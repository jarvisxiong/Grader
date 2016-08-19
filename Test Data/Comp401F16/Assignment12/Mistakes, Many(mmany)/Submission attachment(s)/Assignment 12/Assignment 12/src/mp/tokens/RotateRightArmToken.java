package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"rotateRightArm"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class RotateRightArmToken extends AValueToken {

	public RotateRightArmToken(String initInput) {
		super(initInput);
	}

}
