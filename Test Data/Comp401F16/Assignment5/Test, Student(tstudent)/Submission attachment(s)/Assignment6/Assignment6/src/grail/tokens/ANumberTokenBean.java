package grail.tokens;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@Tags({"Number"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
public class ANumberTokenBean extends ATokenBean implements NumberTokenBean {
	private int value;
	
	public ANumberTokenBean(String newInput) {
		setInput(newInput);
	}
	
	public void setInput(String newInput) {
		super.setInput(newInput);
		value = Integer.parseInt(newInput);
	}
	
	public int getValue() {
		return value;
	}
}
