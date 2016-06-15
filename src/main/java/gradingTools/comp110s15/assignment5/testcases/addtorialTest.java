package gradingTools.comp110s15.assignment5.testcases;

import framework.execution.BasicRunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class addtorialTest extends BasicTestCase {

	public addtorialTest() {
		super("addtorial test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		BasicRunningProject Project0=RunningProjectUtils.runProject(project, 10, "13\n");
		String output0=Project0.await();
		if(output0.contains("91"))return pass();
		else return fail("did not correctly compute sum of 0 to number given by user");
		
	}

}
