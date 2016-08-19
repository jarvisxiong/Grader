package grail;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"say"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input","Value"})
@EditablePropertyNames({"Input"})
public class Say extends AToken implements Token {
	String value = null;
	public Say(String aString){
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
