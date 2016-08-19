package grail.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
@Tags({"rotateRightArm"})
public class RotateRightArmCommand extends AWordTokenBean {
	
	public RotateRightArmCommand(String newInput) {
		super(newInput);
	}
}
