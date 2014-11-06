package gradingTools.comp110f14.assignment3testcases;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

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
