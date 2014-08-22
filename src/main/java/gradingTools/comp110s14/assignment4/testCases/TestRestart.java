package gradingTools.comp110s14.assignment4.testCases;

import framework.execution.RunningProject;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class TestRestart extends TestGerbilInputWithCommand {

	public TestRestart() {
		super("restart");
	}
	
	@Override
	protected String getSetupInput() {
		return super.getSetupInput() + "\n" + command;
	}

	protected TestCaseResult checkOutput(String prompt, String command, Project project) {
		// Check for correct result of search
		RunningProject runningProject = RunningProjectUtils.runProject(project, 1, getSetupInput() + "\n" + super.getSetupInput());
		String output = runningProject.await();
		String errorOutput = runningProject.getErrorOutput();
		if (errorOutput.length() == 0
				|| !errorOutput.contains("Exception in thread \"main\" java.util.NoSuchElementException: No line found")){
			return fail();
		}
		if (!output.startsWith(prompt)) {
			throw new NotAutomatableException();
		}
		output = output.substring(prompt.length());
		return checkOutputString(output);		
	}
	
	private TestCaseResult fail() {
		return fail("Does not restart on command to restart");
	}

	@Override
	protected TestCaseResult checkOutputString(String result) {
		if (result.toLowerCase().contains("error")) {
			return fail();
		} else {
			return pass();
		}
	}

}
