package grail;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"move"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input","Value"})
@EditablePropertyNames({"Input"})
public class Move extends AToken implements Word {
	String value = null;
	public Move(String aString){
		input = aString;
		value = input.toLowerCase();
	}
	public String getValue(){
		return value;
	}
	public void setInput(String aString){
		input = aString;
		value = input.toLowerCase();
	}
}
