package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"sleep"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class SleepToken extends AValueToken {

	public SleepToken(String initInput) {
		super(initInput);
	}

}
