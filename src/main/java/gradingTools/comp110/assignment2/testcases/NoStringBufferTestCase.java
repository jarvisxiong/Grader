package gradingTools.comp110.assignment2.testcases;

import java.io.IOException;

import org.apache.commons.io.FileUtils;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;

public class NoStringBufferTestCase extends BasicTestCase {

	public NoStringBufferTestCase() {
		super("No StringBuffer test case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		if (project.getClassesManager().isEmpty())
			throw new NotGradableException();

		for (ClassDescription description : project.getClassesManager().get()
				.getClassDescriptions()) {
			try {

				// Get the comment free code
				String code = FileUtils.readFileToString(description.getSource());
				code = code.replaceAll("(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*)",
						"");

				// Fail if we find a split()
				if (code.contains("StringBuffer"))
					return fail("No StringBuffer allowed");

			} catch (IOException e) {
				throw new NotGradableException();
			}
		}

		return pass();
	}
}
