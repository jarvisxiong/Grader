package gradingTools.comp110f14.assignment3testcases;

import java.util.Map;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class NegativeBalanceTest extends BasicTestCase {

	Map<SharedOutput, Integer> expectedNegatives;
	Map<SharedOutput, Integer> expectedNonNegatives;
	int overdraftFee;
	
	public NegativeBalanceTest(Map<SharedOutput, Integer> expectedNegatives,
			Map<SharedOutput, Integer> expectedNonNegatives, int overdraftFee) {
		super("NegativeBalanceCase");
		this.expectedNegatives = expectedNegatives;
		this.expectedNonNegatives = expectedNonNegatives;
		this.overdraftFee = overdraftFee;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		boolean detectsNegativeBalance = true;
		boolean correctOverdraftFee = true;
		
		
		try{
			for (SharedOutput sharedOutput : expectedNegatives.keySet()) {
				int expectedVal = expectedNegatives.get(sharedOutput);
				String output = sharedOutput.getOutput(project);
				
				if (!output.contains("negative")) {
					detectsNegativeBalance = false;
				} else {
					int correctBalance = expectedVal-overdraftFee;
					if(!output.contains(""+correctBalance)) {
						correctOverdraftFee = false;
					}
				}
			}
			
			for (SharedOutput sharedOutput : expectedNonNegatives.keySet()) {
				String output = sharedOutput.getOutput(project);
				
				if (output.contains("negative")) {
					detectsNegativeBalance = false;
				}
			}
			
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		
		if (detectsNegativeBalance && correctOverdraftFee) {
			return pass();
		} else if (!detectsNegativeBalance) {
			return fail("Did not detect negative balance");
		} else {
			return partialPass(0.5, "Did not correctly calculate with overdraft fee");
		}
	}

}
