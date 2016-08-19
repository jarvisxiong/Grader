package mp.tokens;

import mp.tokens.Token;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Minus"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})

public abstract class ABasicToken implements Token {
	String inputString;
	public ABasicToken(String initInput){
		setInput(initInput);
	}
	public void setInput(String token){
		inputString = token;
	}
	public String getInput() {
		return inputString;
	}
}
