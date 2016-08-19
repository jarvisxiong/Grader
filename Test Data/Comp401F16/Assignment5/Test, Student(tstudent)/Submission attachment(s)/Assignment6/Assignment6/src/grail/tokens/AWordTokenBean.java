package grail.tokens;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Word"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
public class AWordTokenBean extends ATokenBean implements WordTokenBean {
	private String value;
	
	public AWordTokenBean(String newWordToken) {
		setInput(newWordToken);
	}

	public void setInput(String newToken) {
		super.setInput(newToken);
		value = newToken.toLowerCase();
	}

	public String getValue() {
		return value;
	}
}
