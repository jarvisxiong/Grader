package gradingTools.comp110f15.assignment2.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class IntegerAfterKTest extends BasicTestCase{

	public IntegerAfterKTest() {
		super("Test if integer is required after K is printed");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject run0 =RunningProjectUtils.runProject(project, 10, "");
		String out0 = run0.await();
		RunningProject run1 =RunningProjectUtils.runProject(project, 10, "1\n1\n");
		String out1 = run1.await();
		
		out1 = out1.substring(out0.length());
		if (out1.contains("A: ")) return pass();
		return fail("The program looped when the user did not put in a number for K");
	}

}
