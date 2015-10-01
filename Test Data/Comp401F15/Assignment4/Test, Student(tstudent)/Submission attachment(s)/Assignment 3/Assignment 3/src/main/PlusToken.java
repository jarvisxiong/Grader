package main;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Plus"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})
public class PlusToken implements Token{
	private String input;
	
	public PlusToken(String input){
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
}
