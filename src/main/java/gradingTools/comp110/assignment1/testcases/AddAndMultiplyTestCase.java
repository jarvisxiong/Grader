package gradingTools.comp110.assignment1.testcases;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class AddAndMultiplyTestCase extends PrintAddAndMultiplyTestCase {

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		TestCaseResult superResult = super.test(project, autoGrade);

		if (superResult.getPercentage() == 1.0) {
			return pass();
		}

		String message = superResult.getNotes();
		boolean adds = !message
				.contains("Does not output the correct integer sum of the numbers\n")
				|| !message.contains("Does not output the correct double sum of the numbers\n");
		boolean multiplies = !message
				.contains("Does not output the correct integer product of the numbers\n")
				|| !message.contains("Does not output the correct double product of the numbers\n");

		if (adds && multiplies) {
			return pass();
		}

		throw new NotAutomatableException();

	}
}
