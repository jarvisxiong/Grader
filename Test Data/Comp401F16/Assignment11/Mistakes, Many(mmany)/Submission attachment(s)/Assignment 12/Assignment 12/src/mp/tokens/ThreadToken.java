package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"thread"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class ThreadToken extends AValueToken {

	public ThreadToken(String initInput) {
		super(initInput);
	}

}
