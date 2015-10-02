package gradingTools.comp110f15.assignment2.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class AttemptNumberTest extends BasicTestCase{

	public AttemptNumberTest() {
		super("Test for the correct attempt number printed");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject run0 =RunningProjectUtils.runProject(project, 5, "1\n1\n1");
		String out0 = run0.await();
		out0=out0.replaceAll("\n", " ");
		out0 = out0.toLowerCase();

		if(out0.contains("attempt number 2")) return pass();
		return fail("The path ABDK was taken to get to attempt #2, but it failed to print attempt number 2");
	}

}
