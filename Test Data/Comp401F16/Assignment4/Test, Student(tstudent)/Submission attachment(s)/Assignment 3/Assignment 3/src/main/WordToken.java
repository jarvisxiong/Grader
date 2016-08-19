package main;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Word"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input", "Value"})
@EditablePropertyNames({"Input"})

public class WordToken implements Token, StringValueToken{
	private String input;
	
	public WordToken(String input){
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
	
	public String getValue() {
		return input.toLowerCase();
	}
	
}
