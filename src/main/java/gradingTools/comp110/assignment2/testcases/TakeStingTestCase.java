package gradingTools.comp110.assignment2.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class TakeStingTestCase extends BasicTestCase {

	public TakeStingTestCase() {
		super("Take String Test Case");
	}

	private TestCaseResult testAcceptingInput(Project project, String input)
			throws NotGradableException {

		try {
			RunningProject runningProject = RunningProjectUtils.runProject(project, 3);
			String output = runningProject.await();
			RunningProject runningProjectWithInput = RunningProjectUtils.runProject(project, 3,
					input);
			String outputAfterInput = runningProjectWithInput.await();

			// Now you can test the output for certain things
			if (outputAfterInput.length() > output.length()) {
				return pass();
			} else
				return fail("Did not take in String input");

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		return testAcceptingInput(project, "This_is_a_string\n");

	}
}
