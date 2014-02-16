package gradingTools.comp110.assignment2.testcases;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class ErrorTestCase extends BasicTestCase {

	public ErrorTestCase() {
		super("Error Message Test Case");
	}

	private boolean hasErrorMessage(Project project, String input) throws NotGradableException {

		try {
			RunningProject runningProject = RunningProjectUtils.runProject(project, 3);
			String output = runningProject.await();
			if (output.endsWith("\n")) {
				output = output.substring(0, output.length() - 1);
			}
			RunningProject runningProjectWithInput = RunningProjectUtils.runProject(project, 3,
					input);
			String outputAfterInput = runningProjectWithInput.await();

			// Now you can test that the output has an error message
			if (outputAfterInput.length() > output.length() && outputAfterInput.startsWith(output)) {
				String errorMessage = outputAfterInput.substring(output.length());
				if (errorMessage.toLowerCase().contains("error")) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		if (hasErrorMessage(project, "this string is much longer than is acceptable\n")
				&& !hasErrorMessage(project, "short")) {
			return pass();
		} else {
			return fail("Does not print error when string is longer than 8 characters");
		}

	}
}
