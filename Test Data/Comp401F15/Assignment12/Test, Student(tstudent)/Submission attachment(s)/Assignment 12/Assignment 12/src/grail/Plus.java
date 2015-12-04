package grail;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@Tags ({"Plus"})
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Plus extends GenericToken implements TokenInterface{
	public Plus (String newInput) {input = newInput;}
}
