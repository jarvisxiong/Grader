package gradingTools.comp110s15.assignment7.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class NegBalance extends BasicTestCase {

	public NegBalance() {
		super("negative balance detection test");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"max\nwithdraw\nchecking\n5\n");
		String output0 = Project0.await().toLowerCase();
		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"max\nwithdraw\nchecking\n5\nprint\n");
		String output1 = Project1.await().toLowerCase().substring(output0.length()-1);
		if(output1.contains("-25"))return pass();
		return fail("did not correctly compute negative balance");
	}

}
