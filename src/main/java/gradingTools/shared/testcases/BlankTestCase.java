package gradingTools.shared.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class BlankTestCase extends BasicTestCase{

	public BlankTestCase() {
		super("Blank Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		return fail("This is a blank case meant to be eyeballed by grader....please view students code");
	}

}
