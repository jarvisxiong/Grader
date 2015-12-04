package grail;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@Tags ({"End"})
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class End extends GenericToken implements TokenInterface{
	public End (String newInput) {input = newInput;}
}