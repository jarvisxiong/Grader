package grail;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@Tags ({"Quote"})
@PropertyNames({"Input","Value"})
@EditablePropertyNames({"Input"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Quote extends GenericToken implements QuoteInterface{
	public Quote (String newInput) {input = newInput;}
	public String getValue () {return input.substring(1,input.length()-1);}
}
