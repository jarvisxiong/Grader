package mp.CommanProcessing;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Approach"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({})
@EditablePropertyNames({"Input"})


public class Approach implements DefaultInterface{
	String input;

	public void setInput(String newString) {
		input = newString;
		
	}

	@Override
	public String getInput() {
		return input;
	}

}
