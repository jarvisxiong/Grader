package mp.CommanProcessing;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Number"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"value"})
@EditablePropertyNames({"input"})


public class Number implements NumberInterface , DefaultInterface {

	int value;
	String input;
	
	
	
	
	@Override
	public int getValue() {
		return value;
	}

	@Override
	public void setInput(String newString) {
		input = newString;
		value = Integer.parseInt(input);
	}

	@Override
	public String getInput() {
		return input;
	}

}
