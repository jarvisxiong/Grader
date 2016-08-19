package grail;
import util.annotations.EditablePropertyNames; 
import util.annotations.PropertyNames;
import util.annotations.StructurePattern; 
import util.annotations.StructurePatternNames; 
import util.annotations.Tags;

@Tags({"Start"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class StartTokenImpl extends AToken implements Token{
	public StartTokenImpl(String input){
		super(input);
	}
}
