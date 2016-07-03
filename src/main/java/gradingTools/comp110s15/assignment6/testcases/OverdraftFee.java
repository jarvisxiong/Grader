package gradingTools.comp110s15.assignment6.testcases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
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
		boolean notifyneg=false;
		// deposit 20 and then withdraw 20.1
		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"deposit\n20\nyes\nwithdraw\n20.01\nno\n");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);
		boolean correctneg=false;
		// deposit 20. This should result in negative account balance due to the previous penalty
		RunningProject Project2 = RunningProjectUtils.runProject(project, 10,
				"deposit\n20\nyes\nwithdraw\n25\nyes\ndeposit\n120\nyes\nprint\nno\n");
		String output2 = Project2.await().toLowerCase();
		output2 = output2.substring(output0.length() - 1);


		if( 	output1.contains("negative") || 
				output1.contains("fee") || 
				output1.contains("overdraft") ||
				output1.contains("penalty") ) notifyneg=true;
			// if you can notify user negative balance when withdraw
		if(output2.contains("95"))correctneg=true;
		if(notifyneg&&correctneg)return pass();
		if(!notifyneg&&!correctneg)return fail("Doesn't notify user of overdraft nor does it add overdraft");
		else{
			if(!notifyneg)return partialPass(0.5,"does not notify user of overdraft");
			return partialPass(0.5,"does not correctly compute overdraft fee");
		}
	}
}
