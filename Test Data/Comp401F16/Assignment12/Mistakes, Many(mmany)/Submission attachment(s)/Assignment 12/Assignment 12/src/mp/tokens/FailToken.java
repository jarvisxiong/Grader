package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags("fail")
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})

public class FailToken extends AValueToken {

	public FailToken(String initInput) {
		super(initInput);
	}

}