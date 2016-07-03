package gradingTools.comp110f14.assignment3testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class TransactionPromptTest extends BasicTestCase {

	SharedOutput sharedOutput;
	public TransactionPromptTest(SharedOutput transactionPromptOutput ) {
		super("TransactionPromptCase");
		this.sharedOutput = transactionPromptOutput;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		boolean hasPrompt=false;
		try{
			String output = sharedOutput.getOutput(project).toLowerCase();
			if((output.contains("type") || output.contains("kind")) && output.contains("transaction"));
				hasPrompt=true;
		}
		catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		if(hasPrompt)return pass();
		else return fail("No prompt for the type of transaction.");
	}

}
