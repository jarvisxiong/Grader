package grail.tokens;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Start"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})
public class StartTokenBean extends ATokenBean {

	public StartTokenBean(char newInput) {
		String charToString = "" + newInput;
		setInput(charToString);
	}
}
