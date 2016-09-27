package gradingTools.sharedTestCase.checkstyle;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;

public class CheckStyleVariableHasClassTypeTestCase extends CheckStyleCountingWarningsTestCase {
	;
	public static final String WARNING_NAME = "variableHasClassType";
	public static final String MESSAGE = "Variable types using interface";

	public CheckStyleVariableHasClassTypeTestCase(String aMessage) {
		super(null, aMessage);
	}

	public CheckStyleVariableHasClassTypeTestCase() {
		super(null, MESSAGE);
	}

	public CheckStyleVariableHasClassTypeTestCase(double aPenaltyPerMistake) {
		super(null, MESSAGE, aPenaltyPerMistake);

	}

	@Override
	protected String warningName() {
		return WARNING_NAME;
	}

}
