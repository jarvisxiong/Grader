package gradingTools.comp110s15.assignment4.testcases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class welcometest extends BasicTestCase {

	public welcometest() {
		super("welcome test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0=Project0.await();
		output0=output0.toLowerCase();
		if(output0.contains("which school")) return pass();
		else return fail("does not contain the key phrase");
	}


}
