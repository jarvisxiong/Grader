package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"repeat"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class RepeatToken extends AValueToken {

	public RepeatToken(String initInput) {
		super(initInput);
	}

}
