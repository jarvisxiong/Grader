package grader.requirements.interpreter;

import util.misc.Common;
import grader.requirements.interpreter.specification.CSVRequirementsSpecification;
import gradingTools.assignment7.testCases.GetInvalidTestCase;

public class InterpretedVariablesSubstituter {
	public static final String VAR_PREFIX = "$";
	public static final String ACTUAL_OUTPUT = "$actualoutput";
	public static final String MODEL_OUTPUT = "$modeloutput";
	public static final String INPUT = "$input";
	public static final String FILE_SUFFIX = ".txt";

	public static String getValue(CSVRequirementsSpecification aSpecification, 
			int aRequirementNumber, 
			String anOutput,
			String anExpression) {
		String anExpressionLC = anExpression.toLowerCase();
		if (!anExpression.startsWith(VAR_PREFIX)) {
			return anExpression;
		} else if (ACTUAL_OUTPUT.equals(anExpressionLC)) {
			return getActualOutput(aSpecification, aRequirementNumber, anOutput, anExpression);
		} else if (MODEL_OUTPUT.equals(anExpressionLC)) {
			return getModelOutput(aSpecification, aRequirementNumber, anOutput, anExpression);
		} else if (INPUT.equals(anExpressionLC)) {
			return getInput(aSpecification, aRequirementNumber);
		} else return "";
		
	}
	
	public static boolean isFileName(String aString) {
		return (aString.endsWith(FILE_SUFFIX));
			
	}
	
	public static String toString(String aFileOrText) {
		if (isFileName(aFileOrText))
		    return Common.toText(aFileOrText).toString();
		else
			return aFileOrText;
		
	}
	public static String getActualOutput(CSVRequirementsSpecification aSpecification, 
			int aRequirementNumber, 
			String anOutput,
			String anExpression) {
		return anOutput; // cannot be a file
	}
	public static String getModelOutput(CSVRequirementsSpecification aSpecification, 
			int aRequirementNumber, 
			String anOutput,
			String anExpression) {
		String aModelOutput = aSpecification.getModelOutput(aRequirementNumber);
		return toString(aModelOutput);
	}
	
	public static String getInput(CSVRequirementsSpecification aSpecification, 
			int aRequirementNumber) {
		String anInput = aSpecification.getInput(aRequirementNumber);
		return toString(anInput);
	}

}
