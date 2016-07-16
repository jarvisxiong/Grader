package gradingTools.comp401f16.assignment1.testcases;
import java.util.List;

import util.annotations.Explanation;
@Explanation("Single Token")
public class SingleTokenProductTest extends AbstractScanningTest{
	protected String[][] tokenLines = {{"0202"}};

	@Override
	protected String[] expectedOutputs() {
		return expectedSumOutput();
		
	}  
}

