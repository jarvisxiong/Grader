package grader.requirements.interpreter;

import wrappers.framework.project.ProjectWrapper;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.Feature;
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
		Integer aTimeOut = csvRequirementsSpecification.getTimeOut(featureNumber);
		String output = "";
		RunningProject runningProject = null;
		if (aTimeOut != null) {
		 runningProject = RunningProjectUtils.runProject(project, aTimeOut, anInput);
		output = runningProject.await();
		}
		String aComparator = csvRequirementsSpecification.getChecker(featureNumber);
		InterpretedChecker aChecker = InterpretedCheckerRegistry.getInterpretedChecker(aComparator);
		int numArgs = aChecker.getNumArgs();
		String[] anArgs = new String[numArgs];
		for (int i = 0; i < numArgs; i++) {
			String anArg = csvRequirementsSpecification.getArg(featureNumber, i);
			String anActualArg = InterpretedVariablesSubstituter.getValue(((ProjectWrapper) project).getProject(), csvRequirementsSpecification, featureNumber, output, anArg);
			anArgs[i] = anActualArg;
		}
		InterpretedCheckerResult aResult = aChecker.check(anArgs);
		
//		if (checkable instanceof Feature && aResult.isSucceeded()) {
		if (aResult.isSucceeded()) {
			return pass("");
		} else 
			return fail(aResult.getNotes());
	}

}
