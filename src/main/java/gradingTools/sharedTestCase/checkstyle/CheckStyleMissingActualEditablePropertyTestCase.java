package gradingTools.sharedTestCase.checkstyle;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;

public class CheckStyleMissingActualEditablePropertyTestCase extends CheckStyleCountingWarningsTestCase {
	;
	public static final String WARNING_NAME = "missingActualEditableProperty";
	public static final String MESSAGE = "Missing Actual Editable Property";
	public static final double DEFAULT_PENALTY_PER_MISTAKE = 0.1;

	public CheckStyleMissingActualEditablePropertyTestCase(String aMessage) {
		super(null, aMessage);
		penaltyPerMistake = DEFAULT_PENALTY_PER_MISTAKE;
	}

	public CheckStyleMissingActualEditablePropertyTestCase() {
		super(null, MESSAGE);
		penaltyPerMistake = DEFAULT_PENALTY_PER_MISTAKE;
	}

	public CheckStyleMissingActualEditablePropertyTestCase(double aPenaltyPerMistake) {
		super(null, MESSAGE, aPenaltyPerMistake);

	}

	@Override
	protected String warningName() {
		return WARNING_NAME;
	}

}
