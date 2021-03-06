package gradingTools.comp110f14.assignment3testcases;

import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class SecondTransactionPromptTest extends BasicTestCase {

	SharedOutput firstPromptOutput;
	SharedOutput secondPromptOutput;
	public SecondTransactionPromptTest(SharedOutput firstTransactionPromptOutput,
			SharedOutput secondTransactionPromptOutput) {
		super("SecondTransactionPromptCase");
		this.firstPromptOutput = firstTransactionPromptOutput;
		this.secondPromptOutput = secondTransactionPromptOutput;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		boolean hasPrompt=false;
		try{
			String output1 = firstPromptOutput.getOutput(project).toLowerCase();
			String output2 = secondPromptOutput.getOutput(project).toLowerCase();
			
			if (output1.length() <= output2.length()) {
				output2 = output2.substring(output1.length());
			} else {
				throw new NotGradableException();
			}
			
			if((output2.contains("type") || output2.contains("kind")) && output2.contains("transaction"));
				hasPrompt=true;
		}
		catch (NotRunnableException e) {
			throw new NotGradableException();
		}
		if(hasPrompt)return pass();
		else return fail("No prompt for the second type of transaction.");
	}

}
