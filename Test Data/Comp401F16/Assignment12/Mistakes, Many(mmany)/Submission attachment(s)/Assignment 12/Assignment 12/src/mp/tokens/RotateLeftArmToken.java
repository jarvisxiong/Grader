package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"rotateLeftArm"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class RotateLeftArmToken extends AValueToken {

	public RotateLeftArmToken(String initInput) {
		super(initInput);
	}

}
