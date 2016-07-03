package gradingTools.comp110.assignment2.testcases;

import java.util.regex.Pattern;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PromptTestCase extends BasicTestCase {
	public PromptTestCase() {
		super("Prompt printer test case");
	}

	private TestCaseResult testForStringPrompt(String output) throws NotGradableException {
		String lowercaseOutput = output.trim().toLowerCase();
		boolean containsString = lowercaseOutput.contains("string")
				|| lowercaseOutput.contains("word");
		boolean containsLength = Pattern.matches(
				".*((1)|(one))\\s*(-|(to)|(and))\\s*((8)|(eight)).*", lowercaseOutput);
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
