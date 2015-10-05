package grail.tokens;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@Tags({"rotateLeftArm"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class RotateLeftArmCommand extends AWordTokenBean {
	
	public RotateLeftArmCommand(String newInput) {
		super(newInput);
	}
}
 