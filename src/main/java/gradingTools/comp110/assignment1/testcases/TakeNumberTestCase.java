package gradingTools.comp110.assignment1.testcases;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class TakeNumberTestCase extends BasicTestCase {

	public TakeNumberTestCase() {
		super("TakeNumber Test Case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {
			RunningProject runningProject = RunningProjectUtils.runProject(project, 3);
			String output = runningProject.await();
			int run1 = output.length();
			RunningProject runningProject2 = RunningProjectUtils.runProject(project, 3, "1");
			String output2 = runningProject2.await();
			int run2 = output2.length();
			RunningProject runningProject3 = RunningProjectUtils.runProject(project, 3, "1", "2.5");
			String output3 = runningProject3.await();
			int run3 = output3.length();

			// Now you can test the output for certain things
			if (run3 > run2 && run3 > run1) {
				return pass();
			} else if (run3 == run2 && run2 > run1) {
				return partialPass(.5, "Only takes in one int or double, not both.");
			} else
				return fail("Did not take in any input");
		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}

	}

}
