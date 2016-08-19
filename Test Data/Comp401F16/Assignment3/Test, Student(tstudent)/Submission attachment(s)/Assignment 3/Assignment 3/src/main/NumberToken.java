package main;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Number"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})
public class NumberToken implements Token, IntValueToken{
	private String input;
	
	public NumberToken(String input){
		setInput(input);
	}

	@Override
	public String getInput() {
		return input;
	}

	@Override
	public void setInput(String input) {
		this.input = input;
	}

	public int getValue() {
		return Integer.parseInt(input);
	}
}
