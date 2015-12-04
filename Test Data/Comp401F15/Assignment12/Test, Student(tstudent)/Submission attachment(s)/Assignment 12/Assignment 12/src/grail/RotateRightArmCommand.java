package grail;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"rotateRightArm"})

public class RotateRightArmCommand extends GenericWordToken implements WordInterface{
	public RotateRightArmCommand (String newInput) {input = newInput;}
}
