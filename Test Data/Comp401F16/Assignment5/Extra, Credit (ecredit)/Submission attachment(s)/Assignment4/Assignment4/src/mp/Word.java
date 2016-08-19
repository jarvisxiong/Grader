package mp;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"Word"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"input","value"})
@EditablePropertyNames({"input"})

public class Word implements RawInput, ProcWordValue{
	private String input="";
	private String value;//lower case for the input string
	
	public Word(String input)
	{
		this.input=input;
		//value=input.toLowerCase();//the input not always legal, so need more process here
	}
	public void setInput(String input)
	{
		this.input=input;
		value=input.toLowerCase();//the input not always legal, so need more process here

		//value=input.toLowerCase();//the input not always legal, so need more process here
	}
	
	public String getInput()
	{
		return this.input;
	}
	
	public String getValue()
	{

		return value;
	}
	
}
