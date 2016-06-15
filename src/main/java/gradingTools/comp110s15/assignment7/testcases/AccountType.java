package gradingTools.comp110s15.assignment7.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class AccountType extends BasicTestCase {
	public AccountType() {
		super("tests account type prompt");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"max\n");
		String output0 = Project0.await().toLowerCase();
		boolean dgood=false;
		boolean wgood=false;
		boolean tgood=false;
		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"max\nwithdraw\n");
		String output1 = Project1.await().toLowerCase();
		output1=output1.substring(output0.length()-1);
		if(output1.contains("account"))wgood=true;
		RunningProject Project2 = RunningProjectUtils.runProject(project, 10,
				"max\ndeposit\n");
		String output2 = Project2.await().toLowerCase();
		output2=output2.substring(output0.length()-1);
		if(output2.contains("account"))dgood=true;
		RunningProject Project3 = RunningProjectUtils.runProject(project, 10,
				"max\ntransfer\n");
		String output3 = Project3.await().toLowerCase();
		output3=output3.substring(output0.length()-1);
		if(output3.contains("account"))tgood=true;
		
		if(tgood&&dgood&&wgood)return pass();
		if(!tgood&&!dgood&&!wgood)return fail("did not contain keyword on any of three ops");
		else{
			int numright=3;
			String partial="No keyword on: ";
			if(!tgood){
				partial+="transfer,";
				numright--;
			}
			if(!wgood){
				partial+="withdraw,";
				numright--;
			}
			if(!dgood){
				partial+="deposit,";
				numright--;
			}
			return partialPass(numright/3,partial);
		}
	}
}
