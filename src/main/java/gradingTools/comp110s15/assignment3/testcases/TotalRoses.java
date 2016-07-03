package gradingTools.comp110s15.assignment3.testcases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class TotalRoses extends BasicTestCase {

	public TotalRoses() {
		super("Calculate Total Roses Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		int numRoses = 17;
		int totalRoses = (numRoses * 12);

		RunningProject runningProject = RunningProjectUtils.runProject(project,
				10, "roses" + '\n' + numRoses+'\n');
		String rosesOutput = runningProject.await();

		if (rosesOutput.contains(""+totalRoses)
				) {
			return pass();
		}

		return fail("Did not correctly compute number of roses");

	}
}
