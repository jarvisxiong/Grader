package tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags ({"End"})
@EditablePropertyNames({"End"})
@PropertyNames({"End"})


public class End implements EndInterface{
	
	private String End;
	
	public End(String in){
		setEnd(in);
		getEnd();
	}
	
	public void setEnd(String in){
		End = in;
	}
	
	public String getEnd(){
		return End;
	}
}
