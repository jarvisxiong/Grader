package gradingTools.comp401f16.assignment1.testcases;
import java.util.List;

import util.annotations.Explanation;
@Explanation("Single Token")
public class SingleTokenSumTest extends SingleTokenOutputTest{

	@Override
	protected String[] expectedOutputs() {
		return expectedSumOutputs();
		
	}

}

