package gradingTools.comp110f15.assignment2.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class GameOverTest extends BasicTestCase{

	public GameOverTest() {
		super("Test to see if Game Over is printed");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject run0 =RunningProjectUtils.runProject(project, 5, "1\n1\n1\n1\n1\n1\n1\n1\n1\n");
		String out0 = run0.await();
		out0=out0.replaceAll("\n", " ");
		out0 = out0.toLowerCase();

		if(out0.contains("game over")) return pass();
		return fail("Did not print Game Over at the end of the program");
	}

}
