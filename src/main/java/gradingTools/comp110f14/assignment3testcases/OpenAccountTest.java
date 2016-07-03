package gradingTools.comp110f14.assignment3testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

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
