package gradingTools.comp110f14.assignment3testcases;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class OpenAccountTest extends BasicTestCase {
	SharedOutput sharedOutput;
	
	public OpenAccountTest(SharedOutput output) {
		super("OpenAccountCase");
		this.sharedOutput = output;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		boolean hasPrompt=false;
		try{
			String output = sharedOutput.getOutput(project).toLowerCase();
			if(output.contains("account") && output.contains("?"));
				hasPrompt=true;
		}
		catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		if(hasPrompt)return pass();
		else return fail("No prompt for opening account.");
	}
}
