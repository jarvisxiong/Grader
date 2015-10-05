package grail.tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})
public class ATokenBean implements TokenBean {
	private String input;

	public void setInput(String newInput) {
		input = newInput;
	}
	
	public String getInput() {
		return input;
	}
}
