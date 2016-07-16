package gradingTools.comp401f16.assignment1.testcases;
import java.util.List;

import util.annotations.Explanation;
@Explanation("Single Token")
public class SingleTokenOutputTest extends AbstractScanningTest{
	String token = "12479";
//	String inputWithNoEndingSpace = "12479\n.";
//    String[] expectedOutputs = {"(.*)12479(.*)"};
   
    protected String token() {
    	return token;
    }
    @Override
	protected String inputWithNoEndingSpace() {
		return token() + "\n.";
	}
	@Override
	protected String[] expectedOutputs() {
		return expectedOutputs(token());
	} 
	
	protected String[] expectedOutputs(String aToken) {
		return new String[] {
				toRegex(aToken), // print token,
				toRegex(aToken), // print sum
				toRegex(aToken), // print product
				};
	} 
	 protected  boolean isValidOutput(List<String> anOutput, String[] anExpectedStrings){
		   return isValidOutputInDifferentLines(anOutput, anExpectedStrings);
	 }
}

