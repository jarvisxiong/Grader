package grader.requirements.interpreter;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.requirements.interpreter.checkers.InterpretedChecker;
import grader.requirements.interpreter.checkers.InterpretedCheckerRegistry;
import grader.requirements.interpreter.checkers.InterpretedCheckerResult;
import grader.requirements.interpreter.specification.CSVRequirementsSpecification;
import gradingTools.utils.RunningProjectUtils;

public class AnInterpretedTestCase extends BasicTestCase{
	CSVRequirementsSpecification csvRequirementsSpecification;
	int featureNumber;
	String input;
	
	public AnInterpretedTestCase(String aName, CSVRequirementsSpecification aCSVRequirementsSpecification,
							int aFeatureNum
							) {
		super(aName);
		csvRequirementsSpecification = aCSVRequirementsSpecification;
		featureNumber = aFeatureNum;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		String anInput = InterpretedVariablesSubstituter.getInput(csvRequirementsSpecification, featureNumber);
		int aTimeOut = csvRequirementsSpecification.getTimeOut(featureNumber);
		RunningProject runningProject = RunningProjectUtils.runProject(project, aTimeOut, anInput);
		String output = runningProject.await();
		String aComparator = csvRequirementsSpecification.getComparator(featureNumber);
		InterpretedChecker aChecker = InterpretedCheckerRegistry.getInterpretedChecker(aComparator);
		int numArgs = aChecker.getNumArgs();
		String[] anArgs = new String[numArgs];
		for (int i = 0; i < numArgs; i++) {
			String anArg = csvRequirementsSpecification.getArg(featureNumber, i);
			String anActualArg = InterpretedVariablesSubstituter.getValue(csvRequirementsSpecification, featureNumber, output, anArg);
		}
		InterpretedCheckerResult aResult = aChecker.check(anArgs);
		if (aResult.isSucceeded()) {
			return pass(aResult.getNotes());
		} else 
			return fail(aResult.getNotes());
	}

}
