package grail.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
@Tags({"thread"})
public class ThreadCommand extends AWordTokenBean {

	public ThreadCommand(String newInput) {
		super(newInput);
	}
}
