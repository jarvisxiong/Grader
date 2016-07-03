package gradingTools.comp110f14.assignment4testcases;

import java.util.regex.Pattern;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class NotEnoughInfoTestCase extends BasicTestCase {

	public NotEnoughInfoTestCase() {
		super("Proper handling of gene input without enough information");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject noInfo = RunningProjectUtils.runProject(project, 10,"ba");
		String noInfoOutput=noInfo.await();
		boolean hasMessage=false;
		boolean askAgain=false;
		if(noInfoOutput.toLowerCase().contains("not enough information"))hasMessage=true;
		String[] splittedNoInfoOutput=noInfoOutput.split("\n");
		int length= splittedNoInfoOutput.length;
		Pattern genecrap=Pattern.compile(".*gene|seq|anoth.*");
		if(genecrap.matcher(splittedNoInfoOutput[length-1]).find()||genecrap.matcher(splittedNoInfoOutput[length-2]).find())askAgain=true;
		if(askAgain&&hasMessage)return pass();
		if(!askAgain&&!hasMessage)return fail("did not print out notification correctly nor asked for gene input");
		int numwrong=0;
		String graderMessage="";
		if(!hasMessage){
			graderMessage+="Did not print out the notification exactly as prompt states.";
			numwrong++;
		}
		if(!askAgain){
			graderMessage+="Does not ask for another gene input";
			numwrong++;
		}
		return partialPass(0.5,graderMessage);
	}

}
