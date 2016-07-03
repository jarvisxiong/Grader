package gradingTools.comp110.assignment2.testcases;

import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
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
