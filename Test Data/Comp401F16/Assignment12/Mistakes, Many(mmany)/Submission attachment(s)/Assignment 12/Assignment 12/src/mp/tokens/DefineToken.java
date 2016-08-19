package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"define"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class DefineToken extends AValueToken {

	public DefineToken(String initInput) {
		super(initInput);
	}

}
