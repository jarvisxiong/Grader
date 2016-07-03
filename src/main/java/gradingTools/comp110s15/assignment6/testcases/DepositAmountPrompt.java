package gradingTools.comp110s15.assignment6.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
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
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0 = Project0.await().toLowerCase();
		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
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
