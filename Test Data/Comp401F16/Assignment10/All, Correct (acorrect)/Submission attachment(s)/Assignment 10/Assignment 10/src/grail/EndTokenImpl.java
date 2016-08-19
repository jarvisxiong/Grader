package grail;
import util.annotations.EditablePropertyNames; 
import util.annotations.PropertyNames;
import util.annotations.StructurePattern; 
import util.annotations.StructurePatternNames; 
import util.annotations.Tags;

@Tags({"End"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class EndTokenImpl extends AToken implements Token{
	public EndTokenImpl(String input){
		super(input);
	}

}
