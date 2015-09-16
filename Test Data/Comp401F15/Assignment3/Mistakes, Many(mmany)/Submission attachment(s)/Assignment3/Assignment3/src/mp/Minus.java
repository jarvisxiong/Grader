package mp;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"Minus"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"input"})
@EditablePropertyNames({"input"})

public class Minus implements RawInput{
	private String input="";
	
	public Minus(String input)//need or not
	{
		this.input=input;
	}
	
	public void setInput(String input)
	{
		this.input=input;
	}
	
	public String getInput()
	{
		return this.input;
	}
}
