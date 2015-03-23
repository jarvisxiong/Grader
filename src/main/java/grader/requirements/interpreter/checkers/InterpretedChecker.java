package grader.requirements.interpreter.checkers;

import grader.requirements.interpreter.specification.CSVRequirementsSpecification;

public interface InterpretedChecker {
//	public boolean isExpandFiles();
	public int getNumArgs();
	public InterpretedCheckerResult check (String[] anArgs);

}
