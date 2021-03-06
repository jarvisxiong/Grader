package gradingTools.comp110.assignment3.testCases;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class InfiniteLoopComputationTestCase extends BasicTestCase {

	String input1;
	String choice1;
	String input2;
	String choice2;

	public InfiniteLoopComputationTestCase(String input1, String choice1, String input2,
			String choice2) {
		super("Test repeated request of inputs and choices");
		this.input1 = input1;
		this.choice1 = choice1;
		this.input2 = input2;
		this.choice2 = choice2;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		try {
			RunningProject runningProject = RunningProjectUtils.runProject(project, 1, input1,
					choice1);
			String output1 = runningProject.await();
			if (output1.endsWith("\n")) {
				output1 = output1.substring(0, output1.length() - 1);
			}

			RunningProject runningProject2 = RunningProjectUtils.runProject(project, 2, input1,
					choice1, input2, choice2);
			String output2 = runningProject2.await();
			if (output2.startsWith(output1)) {
				if (output2.length() > output1.length() + 1) {
					return pass();
				} else {
					return fail("Does not continually ask for new Strings and calculations to run");
				}
			} else {
				throw new NotAutomatableException();
			}

		} catch (NotRunnableException e) {
			throw new NotAutomatableException();
		}
	}
}
