package mp;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"Minus"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input"})
@EditablePropertyNames({"Input"})

public class Minus implements RawInput{
	private String Input="";
	
	public Minus(String input)//need or not
	{
		this.Input=input;
	}
	
	public void setInput(String input)
	{
		this.Input=input;
	}
	
	public String getInput()
	{
		return this.Input;
	}
}
