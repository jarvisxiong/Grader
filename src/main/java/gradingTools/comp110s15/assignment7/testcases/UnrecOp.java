package gradingTools.comp110s15.assignment7.testcases;

import framework.execution.BasicRunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
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
		BasicRunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String out0=Project0.await().toLowerCase();
		BasicRunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"max\nwoeifjw\n");
		String out1=Project1.await().toLowerCase().substring(out0.length()-1);
		if(out1.contains("unrecognized"))return pass();
		return fail("output did not contain keyword(s) on unrecognized operation");
	}

}
