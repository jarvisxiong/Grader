package gradingTools.comp110file.assignment1.testcases;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class FileAddAndMultiplyTestCase extends FilePrintAddAndMultiplyTestCase {

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
