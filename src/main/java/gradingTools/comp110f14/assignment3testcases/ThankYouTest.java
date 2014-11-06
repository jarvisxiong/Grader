package gradingTools.comp110f14.assignment3testcases;

import java.util.Collection;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class ThankYouTest extends BasicTestCase {

	SharedOutput initialPromptOutputObj;
	SharedOutput noAccountOutputObj;
	Collection<SharedOutput> twoTransactionsOutputObjs;
	
	public ThankYouTest(SharedOutput initialPromptOutput, 
			SharedOutput noAccountOutput, Collection<SharedOutput> twoTransactionsOutputs) {
		super("ThankYouCase");
		this.initialPromptOutputObj = initialPromptOutput;
		this.noAccountOutputObj = noAccountOutput;
		this.twoTransactionsOutputObjs = twoTransactionsOutputs;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		boolean noAccountThanks = true;
		boolean twoTransactionThanks = true;
		
		String initialPromptOutput = initialPromptOutputObj.getOutput(project).toLowerCase();
		
		String noAccountOutput = noAccountOutputObj.getOutput(project).toLowerCase();
		noAccountOutput = noAccountOutput.substring(initialPromptOutput.length());
		if (!noAccountOutput.contains("thank")) {
			noAccountThanks = false;
		}
		
		for (SharedOutput twoTransactionOutputObj : twoTransactionsOutputObjs) {
			String twoTransactionOutput = twoTransactionOutputObj.getOutput(project).toLowerCase();
			twoTransactionOutput = twoTransactionOutput.substring(initialPromptOutput.length());
			
			if (!twoTransactionOutput.contains("thank")) {
				twoTransactionThanks = false;
				break;
			}
		}
		
		if (noAccountThanks && twoTransactionThanks) {
			return pass();
		} else if (!noAccountThanks && !twoTransactionThanks) {
			return fail("No Thank You Message");
		} else if (!noAccountThanks) {
			return partialPass(0.5, "No Thank You Message after not opening an account");
		} else {
			return partialPass(0.5, "No Thank You Message after two transactions");
		}
	}

}
