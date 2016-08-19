package grail;

public class AToken implements Token{
	String string;
	public AToken(String input){
		string=input;
	}

	public void setInput(String input){
		string=input;
	}

	public String getInput(){
		return string;
	}
}
