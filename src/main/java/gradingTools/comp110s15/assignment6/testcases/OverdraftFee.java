package gradingTools.comp110s15.assignment6.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class OverdraftFee extends BasicTestCase {

	public OverdraftFee() {
		super("Overdraft Fee Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0 = Project0.await().toLowerCase();

		// deposit 20 and then withdraw 20.1
		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"deposit\n20\nyes\nwithdraw\n20.01\nno");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);

		// deposit 20. This should result in negative account balance due to the previous penalty
		RunningProject Project2 = RunningProjectUtils.runProject(project, 10,
				"deposit\n20\nyes\nwithdraw\n20.01\nyes\ndeposit\n20\nno");
		String output2 = Project2.await().toLowerCase();
		output2 = output2.substring(output0.length() - 1);

		// print to see if they indeed correctly output negative account balance 
		RunningProject Project3 = RunningProjectUtils.runProject(project, 10, 
				"deposit\n20\nyes\nwithdraw\n20.01\nyes\ndeposit\n20\nyes\nprint\nno");
		String output3 = Project3.await().toLowerCase();
		output3 = output3.substring(output0.length() - 1);

		String negativeBalancePrompt0 = output1;
		String negativeBalancePrompt1 = output2.substring(output1.length()-1);

		if( 	negativeBalancePrompt0.contains("negative") || 
				negativeBalancePrompt0.contains("fee") || 
				negativeBalancePrompt0.contains("overdraft") ||
				negativeBalancePrompt0.contains("penalty") ) {
			// if you can notify user negative balance when withdraw

			if(		negativeBalancePrompt1.contains("negative") || 
					negativeBalancePrompt1.contains("fee") || 
					negativeBalancePrompt1.contains("overdraft") ||
					negativeBalancePrompt1.contains("penalty") ) {
				// if you can notify user negative balance when deposit

				String printOutput1 = output3;
				Pattern out2 = Pattern.compile(".*balance: \\$-20.01\n.*");
				if (out2.matcher(printOutput1).find()) {
					// full credit
					return pass();
				}
				else {
					// internal balance not reflected
					return partialPass(0.8, "Failed to update balance internally");
				}
			}
			else {
				String printOutput1 = output3;
				Pattern out2 = Pattern.compile(".*balance: \\$-20.01\n.*");
				if (out2.matcher(printOutput1).find()) {
					// 
					return partialPass(0.6, "Failed to take penalty for negative balance even if deposit but correct account balance?");
				}
				else {
					// only the base case
					return partialPass(0.4, "Failed to take penalty for negative balance even if deposit and internal balance not updated");
				}
			}

		}
		else {
			// if you cannot notify user negative balance when withdraw
			if(		negativeBalancePrompt1.contains("negative") || 
					negativeBalancePrompt1.contains("fee") || 
					negativeBalancePrompt1.contains("overdraft") ||
					negativeBalancePrompt1.contains("penalty") ) {
				// but you can detect with negative balance when deposit... really necessary case?
				String printOutput1 = output3;
				Pattern out2 = Pattern.compile(".*balance: \\$-20.01\n.*");
				if (out2.matcher(printOutput1).find()) {
					// full credit
					return partialPass(0.6, "Failed to take penalty for negative balance withdrawal but for deposit?");
				}
				else {
					// internal balance not reflected
					return partialPass(0.4, "negative balance is not internally reflected");
				}
			}
			else {
				String printOutput1 = output3;
				Pattern out2 = Pattern.compile(".*balance: \\$-20.01\n.*");
				if (out2.matcher(printOutput1).find()) {
					// 
					return partialPass(0.2, "Failed to take penalty but correct account balance?");
				}
				else {
					// only the base case
					return fail("Does not handle negative balance cases at all");
				}
			}
		}

	}

}
