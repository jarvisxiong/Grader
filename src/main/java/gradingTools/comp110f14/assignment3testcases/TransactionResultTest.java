package gradingTools.comp110f14.assignment3testcases;

import java.util.Map;
import java.util.regex.Pattern;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;

public class TransactionResultTest extends BasicTestCase {

	Map<SharedOutput, Integer> expectedAmounts;
	public TransactionResultTest(Map<SharedOutput, Integer> expectedAmounts ) {
		super("TransactionResultCase");
		this.expectedAmounts = expectedAmounts;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		boolean integerForm = true;
		boolean correctBalance = true;
		try{
			for (SharedOutput sharedOutput : expectedAmounts.keySet()) {
				int expectedVal = expectedAmounts.get(sharedOutput);
				String output = sharedOutput.getOutput(project);
				
				if (!output.contains(""+expectedVal)) {
					correctBalance = false;
				} else {
					if (expectedVal > 0) {
						if (output.contains("-"+expectedVal)) {
							correctBalance = false;
							continue;
						}
					}
					if (Pattern.compile(expectedVal+".\\d+").matcher(output).find()) {
						integerForm = false;
					}
					
				}
			}
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		
		if (integerForm && correctBalance) {
			return pass();
		} else if (!correctBalance) {
			return fail("Incorrect balance");
		} else {
			return partialPass(0.5, "Balance not in integer form");
		}
	}

}
