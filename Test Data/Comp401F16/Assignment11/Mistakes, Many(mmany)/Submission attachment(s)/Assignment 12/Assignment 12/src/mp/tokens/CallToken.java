package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"call"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class CallToken extends AValueToken{

	public CallToken(String initInput) {
		super(initInput);
	}
}
