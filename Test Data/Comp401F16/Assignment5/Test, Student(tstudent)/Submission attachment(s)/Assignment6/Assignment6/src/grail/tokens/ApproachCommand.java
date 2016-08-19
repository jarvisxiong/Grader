package grail.tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@Tags({"approach"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
public class ApproachCommand extends AWordTokenBean {
	
	public ApproachCommand(String newInput) {
		super(newInput);
	}
}
