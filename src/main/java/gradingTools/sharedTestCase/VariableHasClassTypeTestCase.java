package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;

public class VariableHasClassTypeTestCase extends CountingWarningsTestCase {
	;
	public static final String WARNING_NAME = "variableHasClassType";
	public static final String MESSAGE = "Variable types using interface";

	public VariableHasClassTypeTestCase(String aMessage) {
		super(null, aMessage);
	}

	public VariableHasClassTypeTestCase() {
		super(null, MESSAGE);
	}

	public VariableHasClassTypeTestCase(double aPenaltyPerMistake) {
		super(null, MESSAGE, aPenaltyPerMistake);

	}

	@Override
	protected String warningName() {
		return WARNING_NAME;
	}

}
