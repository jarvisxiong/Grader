package gradingTools.comp110s15.assignment7.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class UnrecOp extends BasicTestCase {

	public UnrecOp() {
		super("Unrecognized option case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String out0=Project0.await().toLowerCase();
		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"max\nwoeifjw\n");
		String out1=Project1.await().toLowerCase().substring(out0.length()-1);
		if(out1.contains("unrecognized"))return pass();
		return fail("output did not contain keyword(s) on unrecognized operation");
	}

}
