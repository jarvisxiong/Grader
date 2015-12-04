package grail;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@Tags ({"Minus"})
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Minus extends GenericToken implements TokenInterface {
	public Minus (String newInput) {input = newInput;}
}