package mp;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"Number"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"input","value"})
@EditablePropertyNames({"input"})

public class Number implements RawInput, ProcNumberValue{
	private String input="";
	private int value;
	
	public Number(String input)
	{
		this.input=input;
		value=Integer.parseInt(input);//the input not always legal, so need more process here
	}
	public void setInput(String input)
	{
		this.input=input;
		value=Integer.parseInt(input);//the input not always legal, so need more process here
	}
	
	public String getInput()
	{
		return this.input;
	}
	
	public int getValue()
	{
		return value;
	}
	
}
