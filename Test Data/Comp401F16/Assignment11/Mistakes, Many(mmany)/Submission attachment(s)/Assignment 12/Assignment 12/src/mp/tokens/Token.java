package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})

public interface Token {
	public void setInput(String initWord);
	public String getInput();
}
