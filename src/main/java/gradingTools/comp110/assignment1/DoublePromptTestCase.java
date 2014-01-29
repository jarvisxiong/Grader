package gradingTools.comp110.assignment1;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class DoublePromptTestCase extends BasicTestCase {

	public DoublePromptTestCase() {
		super("Double Prompt Test Case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		try {
			RunningProject runningProject = project.launch("", 3);
			String output = runningProject.await();
			int run1 = output.length();
			RunningProject runningProject2 = project.launch("1", 3);
			String output2 = runningProject2.await();
			output2 = output2.substring(run1).trim();

			// Now you can test the output for certain things
			if (output2.toLowerCase().contains("dec")
					|| output2.toLowerCase().contains("doub"))
				return pass();
			else if (output2.trim().length() == 0)
				return fail("Program does not output any double prompt");
			else
				throw new NotGradableException();
		}

		catch (NotRunnableException e) {
			throw new NotGradableException();
		}

	}
}
