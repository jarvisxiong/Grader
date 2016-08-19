package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags("pass")
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})

public class PassToken extends AValueToken {

	public PassToken(String initInput) {
		super(initInput);
	}

}