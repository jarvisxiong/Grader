package gradingTools.comp110.assignment4.testCases;

import framework.execution.RunningProject;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class TestQuit extends TestGerbilInputWithCommand {

	public TestQuit() {
		super("quit");
	}
	
	@Override
	protected TestCaseResult checkOutputString(String result) {
		if (result.length() > 0) {
			return fail("Did not properly quit");
		} else {
			return pass();
		}
	}
	
	protected TestCaseResult checkOutput(String prompt, String command, Project project) {
		RunningProject runningProject = RunningProjectUtils.runProject(project, 1, getSetupInput() + "\n" + command);
		runningProject.await();
		String output = runningProject.getErrorOutput();
		return checkOutputString(output);
	}
}
