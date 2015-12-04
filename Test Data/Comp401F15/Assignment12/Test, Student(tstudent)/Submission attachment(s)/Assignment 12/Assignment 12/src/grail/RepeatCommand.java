package grail;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"repeat"})

public class RepeatCommand extends GenericWordToken implements WordInterface{
	public RepeatCommand (String newInput) {input = newInput;}
}
