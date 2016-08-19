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

public class NumberTokenImpl extends AToken implements TokenIntValue{
	//String numString;
	
	public NumberTokenImpl(String input){
		super(input);
	}
	public int getValue(){
		return Integer.parseInt(string);
	}
}
