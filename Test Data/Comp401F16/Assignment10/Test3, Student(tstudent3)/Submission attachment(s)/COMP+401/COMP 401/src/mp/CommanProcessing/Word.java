package mp.CommanProcessing;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Word"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "input" , "value"})
@EditablePropertyNames({"Input"})


public class Word implements WordInterface , DefaultInterface{

	String value;
	String input;
	
	
	
	

	public String getValue() {
		return value;
	}


	public void setInput(String newString) {
		input = newString;
		value = input.toLowerCase();
		
	}


	public String getInput() {
		return input;
	}

	
}
