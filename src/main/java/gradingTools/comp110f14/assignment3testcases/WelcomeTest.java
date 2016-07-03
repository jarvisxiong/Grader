package gradingTools.comp110f14.assignment3testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

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
