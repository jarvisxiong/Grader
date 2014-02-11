package gradingTools.comp110.assignment2.testcases;

import java.util.regex.Pattern;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PromptTestCase extends BasicTestCase {
	public PromptTestCase() {
		super("Prompt printer test case");
	}

	private TestCaseResult testForStringPrompt(String output) throws NotGradableException {
		String lowercaseOutput = output.trim().toLowerCase();
		boolean containsString = lowercaseOutput.contains("string")
				|| lowercaseOutput.contains("word");
		boolean containsLength = Pattern.matches(".*1\\s*(-|(to))\\s*8.*", lowercaseOutput);
		if (containsString && containsLength) {
			return pass();
		} else {
			String message = "";
			double score = 1.0;
			if (!containsString) {
				message += "Does not ask the user for a String\n";
				score -= 0.5;
			}
			if (!containsLength) {
				message += "Does not tell the user how long the String should be \n";
				score -= 0.5;
			}
			if (score > 0) {
				return partialPass(score, message);
			} else {
				throw new NotGradableException();
			}
		}
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		try {

			// Get the output when we have no input from the user
			RunningProject noInputRunningProject = RunningProjectUtils.runProject(project, 1);
			String noInputPrompt = noInputRunningProject.await();

			// Check the prompt
			return testForStringPrompt(noInputPrompt);

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
}
