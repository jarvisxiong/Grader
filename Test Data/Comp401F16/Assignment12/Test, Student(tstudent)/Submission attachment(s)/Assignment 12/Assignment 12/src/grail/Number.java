package grail;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@Tags({"Number"})
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Number extends GenericToken implements NumberInterface {
	public Number (String newInput) {setInput(newInput);}
	public int getValue () {return Integer.parseInt(input);}
}
