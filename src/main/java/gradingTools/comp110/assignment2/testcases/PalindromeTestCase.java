package gradingTools.comp110.assignment2.testcases;

import java.util.Random;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PalindromeTestCase extends BasicTestCase {

	public PalindromeTestCase() {
		super("Palindrome Test Case");
	}

	protected boolean resultOfInputContains(Project project, String input, String requiredResultPart)
			throws NotRunnableException, NotAutomatableException {
		RunningProject runningProject = RunningProjectUtils.runProject(project, 3);
		String prompt = runningProject.await();

		RunningProject runningProjectWithInput = RunningProjectUtils.runProject(project, 3, input);
		String output = runningProjectWithInput.await();

		if (!output.startsWith(prompt)) {
			throw new NotAutomatableException();
		}

		return output.substring(prompt.length()).toLowerCase()
				.contains(requiredResultPart.toLowerCase());
	}

	private boolean correctlyChecksForPalindrome(Project project, String palindrome,
			String nonPalindrome) throws NotAutomatableException, NotGradableException {

		try {
			return resultOfInputContains(project, palindrome, "is a palindrome")
					&& resultOfInputContains(project, nonPalindrome, "is not a palindrome");

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}

	private String createPalindrome(int length) {
		Random rand = new Random();
		String result = "";
		if (length % 2 == 1) {
			char randomChar = (char) rand.nextInt(Character.MAX_CODE_POINT + 1);
			result += randomChar;
		}
		for (int i = 0; i < length / 2; i++) {
			char randomChar = (char) rand.nextInt(Character.MAX_CODE_POINT + 1);
			result = randomChar + result + randomChar;
		}
		return result;
	}

	private String createNonPalindrome(int length) {
		Random rand = new Random();
		String result = "";
		for (int i = 0; i < length; i++) {
			char randomChar = (char) rand.nextInt(Character.MAX_CODE_POINT + 1);
			result = randomChar + result + randomChar;
		}
		while (result.charAt(0) == result.charAt(result.length() - 1)) {
			char randomChar = (char) rand.nextInt(Character.MAX_CODE_POINT + 1);
			result = result.substring(0, result.length() - 1) + randomChar;
		}
		return result;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		try {
			String message = "";
			double score = 1.0;
			if (!resultOfInputContains(project, createPalindrome(1), "is a palindrome")) {
				message += "Does not properly check palindromes of length 1\n";
				score -= 0.125;
			}
			for (int length = 2; length <= 8; length++) {
				if (!correctlyChecksForPalindrome(project, createPalindrome(length).toLowerCase(),
						createNonPalindrome(length).toLowerCase())) {
					message += "Does not properly check palindromes of length " + length + "\n";
					score -= 0.125;
				}
			}

			if (score == 1.0) {
				return pass();
			} else {
				return partialPass(score, message);
			}

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
}