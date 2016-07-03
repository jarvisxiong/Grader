package gradingTools.sharedTestCase;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;

public class MultipleRestrictedValsOutsideComments extends BasicTestCase {

	private final Set<String> restrictedStrings;
	private final List<String> restrictedRegexes;
	private final List<String> regexLabels;

	public MultipleRestrictedValsOutsideComments(Set<String> restrictedStrings,
			List<String> restirctedRegexes, List<String> regexLabels) {
		super("No " + restrictedStrings + " Restriction");
		this.restrictedStrings = restrictedStrings;
		this.restrictedRegexes = restirctedRegexes;
		this.regexLabels = regexLabels;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		boolean containedRestrictedString = false;
		for (String restrictedString : restrictedStrings) {
			RestrictedStringOutsideComments testCase = new RestrictedStringOutsideComments(
					restrictedString);

			TestCaseResult result = testCase.test(project, autoGrade);
			if (result.getPercentage() < 1.0) {
				containedRestrictedString = true;
				break;
			}
		}
		if (containedRestrictedString) {
			String restrictionsList = "";
			Iterator<String> iter = restrictedStrings.iterator();
			while (iter.hasNext()) {
				String next = iter.next();
				if (restrictionsList.length() > 0) {
					restrictionsList += ", ";
					if (!iter.hasNext()) {
						restrictionsList += "or ";
					}
				}
				restrictionsList += next;
			}
			return fail("Contains " + restrictionsList);
		}

		for (int i = 0; i < restrictedRegexes.size(); i++) {
			String restrictedRegex = restrictedRegexes.get(i);
			String regexLabel = regexLabels.get(i);

			RestrictedRegexOutsideComments testCase = new RestrictedRegexOutsideComments(
					restrictedRegex, regexLabel);

			TestCaseResult result = testCase.test(project, autoGrade);
			if (result.getPercentage() < 1.0) {
				return fail("Contains " + regexLabel);
			}
		}

		return pass();
	}
}
