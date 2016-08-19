package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"move"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class MoveToken extends AValueToken {

	public MoveToken(String initInput) {
		super(initInput);
	}

}
