package grail;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"define"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input","Value"}) 
@EditablePropertyNames({"Input"})
public class Define  extends WordTokenImpl implements TokenStringValue{
	public Define(String input){
		super(input);
	}
}
