package gradingTools.sharedTestCase;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class Welcome extends BasicTestCase{

	public Welcome() {
		super("Does initial message contain welcome");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0=Project0.await();
		output0=output0.toLowerCase();
		if(output0.contains("welcome")) return pass();
		else return fail("does not contain the keyword specified");
	}

}
