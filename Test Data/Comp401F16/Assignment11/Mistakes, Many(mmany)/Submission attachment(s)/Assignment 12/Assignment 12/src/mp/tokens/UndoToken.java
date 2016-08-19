package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"undo"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class UndoToken extends AValueToken {

	public UndoToken(String initInput) {
		super(initInput);
	}

}
