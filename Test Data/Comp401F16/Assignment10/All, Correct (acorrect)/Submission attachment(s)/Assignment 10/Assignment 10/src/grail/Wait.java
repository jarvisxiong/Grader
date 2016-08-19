package grail;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"wait"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input","Value"}) 
@EditablePropertyNames({"Input"})
public class Wait  extends WordTokenImpl implements TokenStringValue{
	public Wait(String input){
		super(input);
	}
}
