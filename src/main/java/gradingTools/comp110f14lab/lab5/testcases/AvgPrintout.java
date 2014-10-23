package gradingTools.comp110f14lab.lab5.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class AvgPrintout extends BasicTestCase {

	public AvgPrintout() {
		super("Average printout test case.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		RunningProject goo = RunningProjectUtils.runProject(project, 10, "10");
		String output=goo.await();
		if(output.contains("4.5"))return pass();
		return fail("did not correctly compute average");
	}

}
