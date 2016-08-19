package grail;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Number"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input","Value"})
@EditablePropertyNames({"Input"})
public class ANumber extends AToken implements Number {
	public ANumber(String aString){
		input = aString;
		value = Integer.parseInt(input);
	}
	
int value = 0;
public int getValue(){
	return value;
}
public void setInput(String aString){
	input = aString;
	value = Integer.parseInt(input);
}
}
