package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;
import gradingTools.sharedTestCase.checkstyle.CheckStyleCountingWarningsTestCase;

import java.util.List;

public class MagicNumberTestCase extends CheckStyleCountingWarningsTestCase {
	;
	public static final String WARNING_NAME = "magic";
	public static final String MESSAGE = "No magic number";
	public static final double DEFAULT_PENALTY_PER_MISTAKE = 0.1;

	public MagicNumberTestCase(String aMessage) {
		super(null, aMessage);
		penaltyPerMistake = DEFAULT_PENALTY_PER_MISTAKE;
	}

	public MagicNumberTestCase() {
		super(null, MESSAGE);
		penaltyPerMistake = DEFAULT_PENALTY_PER_MISTAKE;
	}

	public MagicNumberTestCase(double aPenaltyPerMistake) {
		super(null, MESSAGE, aPenaltyPerMistake);

	}

	@Override
	protected String warningName() {
		return WARNING_NAME;
	}

}
