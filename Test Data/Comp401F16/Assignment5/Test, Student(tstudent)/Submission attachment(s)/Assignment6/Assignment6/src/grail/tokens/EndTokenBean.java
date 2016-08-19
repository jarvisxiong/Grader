package grail.tokens;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"End"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})

public class EndTokenBean extends ATokenBean {
	
	public EndTokenBean(char newInput) {
		String charToString = "" + newInput;
		setInput(charToString);
	}
}
