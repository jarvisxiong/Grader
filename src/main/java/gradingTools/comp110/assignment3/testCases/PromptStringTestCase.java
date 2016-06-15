package gradingTools.comp110.assignment3.testCases;



import framework.execution.BasicRunningProject;
import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PromptStringTestCase extends BasicTestCase {
	public PromptStringTestCase() {
		super("Prompt printer test case");
	}

	private TestCaseResult testForStringPrompt(String output) throws NotGradableException {
		String lowercaseOutput = output.trim().toLowerCase();
		boolean containsString = lowercaseOutput.contains("string")
				|| lowercaseOutput.contains("word")|| lowercaseOutput.contains("text");
		boolean containsLength = lowercaseOutput.contains("length")||lowercaseOutput.contains("size")
				||lowercaseOutput.contains("line");
		boolean containsComputation = lowercaseOutput.contains("computation")||lowercaseOutput.contains("do");
		if (containsString && containsLength && containsComputation) {
			return pass();
		} else {
			String message = "";
			double score = 1.0;
			if (!containsString) {
				message += "Does not ask the user for a String\n";
				score -= 0.3;
			}
			if (!containsLength) {
				message += "Does not mention anything about the String length\n";
				score -= 0.3;
			}
			if(!containsComputation){
				message += "Does not ask the user for a computation\n";
				score -= 0.4;
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

			// Get the output after inputting one string, as both prompts should now be up
			BasicRunningProject oneInputRunningProject = RunningProjectUtils.runProject(project, 1, "hello");
			String oneInputPrompt = oneInputRunningProject.await();

			// Check the prompt
			return testForStringPrompt(oneInputPrompt);

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
}