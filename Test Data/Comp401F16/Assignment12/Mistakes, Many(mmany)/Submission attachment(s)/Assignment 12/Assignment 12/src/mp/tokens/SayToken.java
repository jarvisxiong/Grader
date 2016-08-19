package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"stay"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class SayToken extends AValueToken {

	public SayToken(String initInput) {
		super(initInput);
	}

}
