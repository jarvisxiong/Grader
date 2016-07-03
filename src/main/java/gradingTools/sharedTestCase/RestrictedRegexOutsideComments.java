package gradingTools.sharedTestCase;

import java.util.regex.Pattern;

import grader.basics.junit.TestCaseResult;

public class RestrictedRegexOutsideComments extends RestrictedStringOutsideComments {

	private final Pattern restrictedRegex;
	private final String regexLabel;

	public RestrictedRegexOutsideComments(String restrictedRegex, String regexLabel) {
		super("No " + regexLabel + " Restriction");
		this.restrictedRegex = Pattern.compile(restrictedRegex);
		this.regexLabel = regexLabel;
	}

	@Override
	protected boolean containsRestrictedString(String str) {
		return restrictedRegex.matcher(str).find();
	}

	@Override
	protected TestCaseResult restrictedStringOccurred() {
		return fail(regexLabel + " not allowed");
	}
}
