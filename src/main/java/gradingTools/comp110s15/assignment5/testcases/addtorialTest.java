package gradingTools.comp110s15.assignment5.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class addtorialTest extends BasicTestCase {

	public addtorialTest() {
		super("addtorial test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject Project0=RunningProjectUtils.runProject(project, 10, "13\n");
		String output0=Project0.await();
		if(output0.contains("91"))return pass();
		else return fail("did not correctly compute sum of 0 to number given by user");
		
	}

}
