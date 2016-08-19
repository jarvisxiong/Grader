package mp.CommanProcessing;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
@StructurePattern("Bean Pattern")
@PropertyNames({"UppercaseLetters","InputString"})
@EditablePropertyNames({"InputString" })


public class AnUppercaseFilter implements UppercaseFilter {
	
	String inputString;
	String uppercaseLetters = "";
	
	private void uppercaseFinder()
	{
		uppercaseLetters = "";
		for (int index = 0 ; index < inputString.length() -1 ; index++ )
		{
			if ((inputString.charAt(index)>=65) && (inputString.charAt(index) <= 90))
			{
				uppercaseLetters = uppercaseLetters + inputString.substring(index,index+1);
			}
		}		
	}
	
	public String getUppercaseLetters() {
		System.out.println("getUppercaseLetters");
		return uppercaseLetters;
		
	}

	public void setInputString(String newString) {
		inputString = newString;
		System.out.println("setInputString");
		uppercaseFinder();
	}
	
	public String getInputString(){
		System.out.println("getInputString");
		return inputString;
	}

	
	
	


	
}

