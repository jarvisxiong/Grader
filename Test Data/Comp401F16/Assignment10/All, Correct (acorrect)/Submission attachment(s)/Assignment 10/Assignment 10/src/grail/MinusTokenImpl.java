package grail;
import util.annotations.EditablePropertyNames; 
import util.annotations.PropertyNames;
import util.annotations.StructurePattern; 
import util.annotations.StructurePatternNames; 
import util.annotations.Tags;

@Tags({"Minus"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class MinusTokenImpl extends AToken implements Token{
	public MinusTokenImpl(String input){
		super(input);
	}
}
