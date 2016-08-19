package tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags ({"Start"})
@EditablePropertyNames({"Start"})
@PropertyNames({"Start"})

public class Start implements StartInterface {
	
	private String Start;
	
	public Start(String in){
		setStart(in);
		getStart();
	}
	
	public void setStart(String in){
		Start = in;
	}
	
	public String getStart(){
		return Start;
	}
	
}
