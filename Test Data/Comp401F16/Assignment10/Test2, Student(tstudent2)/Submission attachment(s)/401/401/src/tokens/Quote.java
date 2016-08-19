package tokens;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@Tags ({"Quote"})
@EditablePropertyNames({"Quote"})
@PropertyNames({"Quote"})


public class Quote implements QuoteInterface{
	
	private String Quote;
	
	public Quote(String in){
		setQuote(in);
		getQuote();
	}
	public String getQuote(){
		return Quote;
	}
	
	public void setQuote(String token){
		Quote = token;
	}
	
}
