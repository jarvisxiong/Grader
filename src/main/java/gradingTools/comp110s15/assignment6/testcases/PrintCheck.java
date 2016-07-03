package gradingTools.comp110s15.assignment6.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PrintCheck extends BasicTestCase {

	public PrintCheck() {
		super("PrintCheck test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {

		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0 = Project0.await().toLowerCase();

		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"deposit\n145\nyes\nwithdraw\n25\nno\n");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);

		
		RunningProject Project4 = RunningProjectUtils
				.runProject(project, 10,
						"deposit\n150\nyes\nwithdraw\n25\nyes\nwithdraw\n60\nyes\nprint\nno\n");
		String output4 = Project4.await().toLowerCase();
		output4 = output4.substring(output0.length() - 1);

		String printOutput1 = output4.substring(output1.length() - 1);

		boolean trans= (printOutput1.contains("150")&&printOutput1.contains("-60")&&printOutput1.contains("-25"));
		boolean with = printOutput1.contains("150"); // not
		boolean depo = printOutput1.contains("42.5");															// relevant
		boolean balance = printOutput1.contains("65");

		if (trans&&with&&depo&&balance) {
			return pass();
		}
		int numwrong=0;
		String partial="";
		if(!trans){
			numwrong++;
			partial+="did not print out 3 most recent transactions\n";
		}
		if(!balance){
			numwrong++;
			partial+="did not print out current account balance\n";
		};
		if(!depo){
			numwrong++;
			partial+="did not correctly compute/print average deposit amount";
		}
		if(!with){
			numwrong++;
			partial+="did not correctly compute/print average withdraw amount";
		}
		
		if(numwrong==4){
		return fail("did not correctly print 3 most recent transactions, compute average deposit amount, or computes average withdraw amount.");
		}
		return partialPass(numwrong/4,partial);
	}

}