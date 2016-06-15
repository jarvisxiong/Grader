package gradingTools.comp110s15.assignment3.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
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
