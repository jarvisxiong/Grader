package mp;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"Number"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input","Value"})
@EditablePropertyNames({"Input"})

public class Number implements RawInput, ProcNumberValue{
	private String Input="";
	private int Value;
	
	public Number(String input)
	{
		this.Input=input;
		Value=Integer.parseInt(input);//the input not always legal, so need more process here
	}
	public void setInput(String input)
	{
		this.Input=input;
		Value=Integer.parseInt(input);//the input not always legal, so need more process here
	}
	
	public String getInput()
	{
		return this.Input;
	}
	
	public int getValue()
	{
		return Value;
	}
	
}
