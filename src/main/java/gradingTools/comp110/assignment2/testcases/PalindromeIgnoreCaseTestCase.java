package gradingTools.comp110.assignment2.testcases;

import framework.execution.NotRunnableException;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class PalindromeIgnoreCaseTestCase extends PalindromeTestCase {

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		try {

			String casedPalindrome = "A!!a";

			if (resultOfInputContains(project, casedPalindrome, "is a palindrome")) {
				return pass();
			} else {
				return fail("Does not ignore case when detecting palindromes");
			}

		} catch (NotRunnableException e) {
			throw new NotGradableException();
		}
	}
}
