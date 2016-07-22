package gradingTools.comp401f16.assignment1.testcases;
import java.util.List;

import util.annotations.Explanation;
@Explanation("Single Token")
public class SingleTokenOutputTest extends AbstractNumberScanningTest{
	protected String[][] tokenLines = {{"0202"}};
	@Override
	protected String[][] tokenLines() {
		// TODO Auto-generated method stub
		return tokenLines;
	}
	@Override
	protected String[] expectedOutputs() {
		return expectedTokenOutputs();
		
	}  
}

