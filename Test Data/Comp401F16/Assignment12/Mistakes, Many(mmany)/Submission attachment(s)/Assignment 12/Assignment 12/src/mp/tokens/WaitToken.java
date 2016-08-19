package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"wait"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class WaitToken extends AValueToken {

	public WaitToken(String initInput) {
		super(initInput);
	}

}
