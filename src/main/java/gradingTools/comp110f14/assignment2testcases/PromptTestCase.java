package gradingTools.comp110f14.assignment2testcases;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PromptTestCase extends BasicTestCase {

	public PromptTestCase() {
		super("Prompt Test Case for Deposit/Withdrawl");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		BasicRunningProject Project0 = RunningProjectUtils.runProject(project, 10,"");
		String output0=Project0.await().toLowerCase();
		boolean hasdepo=false;
		if(output0.contains("depo"))hasdepo=true;
		
		BasicRunningProject Project1 = RunningProjectUtils.runProject(project,10,"5");
		String output1=Project1.await().toLowerCase();
		boolean haswithd=false;
		if(output1.contains("withd"))haswithd=true;
		
		if(hasdepo&&haswithd)return pass();
		if(!hasdepo&&haswithd)return partialPass(0.5,"has withdrawl prompt but not deposit prompt");
		if(hasdepo&&!haswithd)return partialPass(0.5,"has deposit prompt but not withdrawl prompt");
		return fail("no prompt seen for deposit or withdrawl");
	}

}
