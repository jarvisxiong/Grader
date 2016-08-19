package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"proceedAll"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class ProceedAllToken extends AValueToken {

	public ProceedAllToken(String initInput) {
		super(initInput);
	}

}
