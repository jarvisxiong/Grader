package mp;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"Word"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Input","Value"})
@EditablePropertyNames({"Input"})

public class Word implements RawInput, ProcWordValue{
	private String Input="";
	private String Value;//lower case for the input string
	
	public Word(String input)
	{
		this.Input=input;
		Value=input.toLowerCase();//the input not always legal, so need more process here
	}
	public void setInput(String input)
	{
		this.Input=input;
		Value=input.toLowerCase();//the input not always legal, so need more process here
	}
	
	public String getInput()
	{
		return this.Input;
	}
	
	public String getValue()
	{
		return Value;
	}
	
}
