package gradingTools.comp110s15.assignment6.testcases;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class DepositAmountPrompt extends BasicTestCase {

	public DepositAmountPrompt() {
		super("Deposit Amount Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		BasicRunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0 = Project0.await().toLowerCase();
		BasicRunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"deposit\n");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);
		if(output1.contains("how much") || output1.contains("money") || 
				output1.contains("amount") || output1.contains("deposit") )
			return pass();
		else
			return fail("did not asked deposit amount");
	}

}
