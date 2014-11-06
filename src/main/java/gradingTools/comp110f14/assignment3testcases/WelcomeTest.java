package gradingTools.comp110f14.assignment3testcases;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class WelcomeTest extends BasicTestCase {

	SharedOutput sharedOutput;
	
	public WelcomeTest(SharedOutput output) {
		super("WelcomeCase");
		this.sharedOutput = output;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		boolean hasWelcome=false;
		try{
			
			String output = sharedOutput.getOutput(project).toLowerCase();
			if(output.contains("welcome")||output.contains("hi")||output.contains("hello"))
				hasWelcome=true;
		}
		catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		if(hasWelcome)return pass();
		else return fail("No Welcome message");
	}

}
