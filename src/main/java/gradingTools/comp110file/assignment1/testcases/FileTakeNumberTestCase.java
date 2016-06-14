package gradingTools.comp110file.assignment1.testcases;

import util.misc.Common;
import wrappers.framework.project.ProjectWrapper;
import framework.execution.BasicRunningProject;
import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import gradingTools.utils.RunningProjectUtils;

public class FileTakeNumberTestCase extends BasicTestCase {

	public FileTakeNumberTestCase() {
		super("TakeNumber Test Case");
	}

	private TestCaseResult testAcceptingTwoInputs(Project project, String input1, String input2)
			throws NotGradableException {

		try {
            SakaiProject sakaiProject = ((ProjectWrapper) project).getProject();			
			String projectFolderName = sakaiProject.getBinaryProjectFolderName();
			String outputFileName = projectFolderName + "/" + FilePromptTestCase.OUTPUT_FILE_NAME;	;	
			BasicRunningProject runningProject = RunningProjectUtils.runProject(project, 3);
			String output = runningProject.await();
			output = Common.toText(outputFileName).toString();

			int run1 = output.length();
			BasicRunningProject runningProject2 = RunningProjectUtils.runProject(project, 3, input1);
			String output2 = runningProject2.await();
			output2 = Common.toText(outputFileName).toString();
			int run2 = output2.length();
			BasicRunningProject runningProject3 = RunningProjectUtils.runProject(project, 3, input1,
					input2);
			String output3 = runningProject3.await();
			output3 = Common.toText(outputFileName).toString();
			int run3 = output3.length();

			if (runningProject2.getErrorOutput().contains("InputMismatchException")
					|| runningProject3.getErrorOutput().contains("InputMismatchException")) {
				return null;
			}

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

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		// First run with int then double input
		TestCaseResult result = testAcceptingTwoInputs(project, "1", "2.5");
		if (result != null) {
			return result;
		}

		// Then run with double then int input
		result = testAcceptingTwoInputs(project, "1.5", "2");
		if (result != null) {
			return result;
		}

		// If no results found, this test is not automatable
		throw new NotAutomatableException();

	}

}
