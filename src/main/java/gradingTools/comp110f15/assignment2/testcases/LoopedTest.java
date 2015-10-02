package gradingTools.comp110f15.assignment2.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class LoopedTest extends BasicTestCase{

	public LoopedTest() {
		super("Test to the program to see if it looped 3 times");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject run0 =RunningProjectUtils.runProject(project, 10, "1\n1\n1\n1\n1\n1\n1\n1\n1\n");
		String out0 = run0.await();
		String[] lines = out0.split("\n");
		
		if(lines.length>=46) return pass();
		return fail("Did not loop 3 times");
	}

}
