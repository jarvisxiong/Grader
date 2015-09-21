package gradingTools.comp110f15.assignment1.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class WelcometTest extends BasicTestCase {

	public WelcometTest() {
		super("Do they welcome the user?");
		
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject p0=RunningProjectUtils.runProject(project, 10,"");
		String out0=p0.await();
		boolean welcome=false;
		boolean football=false;
		if(out0.toLowerCase().contains("welcome")) welcome=true;
		if(out0.toLowerCase().contains("football"))football=true;
		if(welcome&&football)return pass();
		if(!welcome&&!football)return fail("Your welcome statment did not contain welcome or football");
		if(!welcome)return partialPass(0.5,"Your welcome statement did not contain the word welcome");
		return partialPass(0.5,"Your welcome statement did not contain the word football");
	}

}
