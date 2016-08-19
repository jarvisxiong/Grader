package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"redo"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class RedoToken extends AValueToken {

	public RedoToken(String initInput) {
		super(initInput);
	}

}
