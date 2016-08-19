package tokens;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@Tags ({"Number"})
@EditablePropertyNames({"Input"})
@PropertyNames({"Input","Value"})

public class Number implements NumberEditable, NumberRead{
	
	// Establish private parameters
	private String Input;
	private int Value;
	
	
	// Establishing the constructor
	public Number(String in){
		setNumber(in);
		getInput();
		Value = Integer.parseInt(Input);
		
	}

	// Establishing Number token getter that return Value
	public int getNumber(){
		return Value;
	}
	
	// Establishing Number token setter that set Input
	public void setNumber(String token){
		Input = token;
	}	
	
	// Establishing Number token getter that return Input
	public String getInput(){
		return Input;
	}
}
