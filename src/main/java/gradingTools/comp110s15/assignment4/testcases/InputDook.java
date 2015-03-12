package gradingTools.comp110s15.assignment4.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class InputDook extends BasicTestCase {

	Pattern getOut = Pattern.compile(".*get.*out.*");

	public InputDook() {
		super("Test Case for Dook input");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		int passCount = 2;
		String partialReply = "Did not handle Dook correctly ";
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0 = Project0.await().toLowerCase();

		RunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"duke\n");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);

		if (!getOut.matcher(output1).find()) {
			passCount--;
		}

		RunningProject Project2 = RunningProjectUtils.runProject(project, 10,
				"DukE\n");
		String output2 = Project2.await().toLowerCase();
		output2 = output2.substring(output0.length() - 1);

		if (!getOut.matcher(output2).find()) {
			passCount--;
			partialReply += "-- Did not handle (ignore case) ";
		}

		if (!output1.equals(output2)) {
			passCount--;
			partialReply += "-- string (duke) and string (DukE) did not produce the same results";
		}

		if (passCount == 2) {
			return pass();
		}
		else if (passCount < 2 && passCount > 0) {
			return partialPass(0.5, partialReply);
		}
		return fail(partialReply);

	}

}
