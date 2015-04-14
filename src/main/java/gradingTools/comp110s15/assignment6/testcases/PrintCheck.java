package gradingTools.comp110s15.assignment6.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
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
				"deposit\n45\nyes\ndeposit\n125\nyes\ndeposit\n60\nno");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);

		// deposit
		RunningProject Project2 = RunningProjectUtils
				.runProject(project, 10,
						"deposit\n45\nyes\ndeposit\n125\nyes\ndeposit\n60\nyes\nprint\nno");
		String output2 = Project2.await().toLowerCase();
		output2 = output2.substring(output0.length() - 1);

		// withdraw
		RunningProject Project3 = RunningProjectUtils
				.runProject(project, 10,
						"deposit\n145\nyes\nwithdraw\n25\nyes\nwithdraw\n60\nyes\nprint\nno");
		String output3 = Project3.await().toLowerCase();
		output3 = output3.substring(output0.length() - 1);

		RunningProject Project4 = RunningProjectUtils
				.runProject(project, 10,
						"deposit\n145\nyes\nwithdraw\n25\nyes\nwithdraw\n60\nyes\nprint\nno");
		String output4 = Project4.await().toLowerCase();
		output4 = output4.substring(output0.length() - 1);

		String printOutput1 = output4.substring(output1.length() - 1);

		Pattern out1 = Pattern
				.compile("deposit: \\$145\nwithdraw: \\$25\nwithdraw: \\$60\n");
		Pattern out2 = Pattern.compile("current account balance: \\$60\n"); // not
																			// relevant
		Pattern out3 = Pattern.compile("average deposit amount: \\$145\n");
		Pattern out4 = Pattern.compile("average withdraw amount: \\$47.50\n");
		Pattern printOut = Pattern.compile(".*" + out1 + out2 + out3 + out4
				+ ".*");

		if (printOut.matcher(printOutput1).find()) {
			return pass();
		}
		/*
		Pattern printOut1 = Pattern.compile(".*" + out1 + ".*"), printOut2 = Pattern
				.compile(".*" + out2 + ".*"), printOut3 = Pattern.compile(".*"
				+ out3 + ".*"), printOut4 = Pattern.compile(".*" + out4 + ".*");
		if (printOut.matcher(printOutput1).find()) {
		 return pass();
		}
		 else if(!(printOut.matcher(printOut1).find())){ 
		 return partialPass(0.5,"Did not print last three transactions correctly");
		} else if!(printOut.matcher(printOut3).find()){
		 return partialPass(0.75, "Did not compute deposit average correctly");
		} else if!(printOut.matcher(printOut4).find()){
		  return partialPass(0.75,"Did not compute withdraw average correctly"); 
		} else{
		return fail(); 
		}
		 */

		return fail("did not correctly print 3 most recent transactions, compute average deposit amount, or computes average withdraw amount.");

	}

}