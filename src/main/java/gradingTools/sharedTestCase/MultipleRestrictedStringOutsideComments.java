package gradingTools.sharedTestCase;

import java.util.Iterator;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class MultipleRestrictedStringOutsideComments extends BasicTestCase {

	private final Set<String> restrictedStrings;

	public MultipleRestrictedStringOutsideComments(Set<String> restrictedStrings) {
		super("No " + restrictedStrings + " Restriction");
		this.restrictedStrings = restrictedStrings;
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

		return pass();
	}
}
