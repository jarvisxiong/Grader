package gradingTools.comp110.assignment2.testcases;

import framework.execution.NotRunnableException;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class ErrorTestCase extends PalindromeTestCase {

	public ErrorTestCase() {
		super("Error Message Test Case");
	}

	private boolean hasErrorMessage(Project project, String input) throws NotGradableException,
			NotAutomatableException {

		try {
			RunningProject runningProject = RunningProjectUtils.runProject(project, 1);
			String prompt = runningProject.await();
			if (prompt.endsWith("\n")) {
				prompt = prompt.substring(0, prompt.length() - 1);
			}

			String[] isError = { "Error" };
			String[] isNotError = {};

			return resultOfInputMatches(project, prompt, input, isError, isNotError);

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		if (hasErrorMessage(project, "this_string_is_much_longer_than_is_acceptable\n")
				&& !hasErrorMessage(project, "short")) {
			return pass();
		} else {
			return fail("Does not print error when string is longer than 8 characters");
		}

	}
}
