package gradingTools.comp110f15lab.lab2.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class NotMultOfThree extends BasicTestCase {

	public NotMultOfThree() {
		super("Notification of not mult of three");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException,
			NoSuchFieldException {
		RunningProject p0 = RunningProjectUtils.runProject(project, 10,
				"3\n3\n6\n9\n");
		String out0=p0.await();
		if(out0.contains("not a multiple of 3"))return fail("You tell the user they did not put in a multiple of three when in fact that never happened.");
		RunningProject p1 = RunningProjectUtils.runProject(project, 10,
				"3\n3\n6\n7\n");
		String out1=p1.await();
		if(out1.contains("not a multiple of 3")) return pass();
		return fail(" You do not notify the user that they did not enter a multiple of three");
	}

}
