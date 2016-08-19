package grail;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"Token"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})
public class AToken implements Token {
	String input = "";
	
	public String getInput(){
		return input;
	}
	public void setInput(String aString){
		input += aString;
	}
}
