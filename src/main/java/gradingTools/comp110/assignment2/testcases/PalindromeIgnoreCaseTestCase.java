package gradingTools.comp110.assignment2.testcases;

import framework.execution.NotRunnableException;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class PalindromeIgnoreCaseTestCase extends PalindromeTestCase {

	public PalindromeIgnoreCaseTestCase() {
		super("Palindrome ignoring capitalization test case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		try {

			String casedPalindrome = "A!bB!a";

			RunningProject runningProject = RunningProjectUtils.runProject(project, 1);
			String prompt = runningProject.await();
			if (prompt.endsWith("\n")) {
				prompt = prompt.substring(0, prompt.length() - 1);
			}

			String[] isPalindrome = { "is a palindrome" };
			String[] isNotPalindrome = { "is not a palindrome", "Error" };

			if (resultOfInputMatches(project, prompt, casedPalindrome, isPalindrome,
					isNotPalindrome)) {
				return pass();
			} else {
				return fail("Does not ignore case when detecting palindromes");
			}

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
}
