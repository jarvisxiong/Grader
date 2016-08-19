package mp.CommanProcessing;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"repeat"})

public class Repeat implements WordInterface {

	String value;
	String input;
	
	
	
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setInput(String newString) {
		input = newString;
		value = input.toLowerCase();
		
	}

	@Override
	public String getInput() {
		return input;
	}
}
