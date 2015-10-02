package gradingTools.comp401f15.assignment3.testcases;

import java.util.List;

import framework.grading.testing.TestCaseResult;
import grader.execution.MainClassFinder;
import grader.sakai.project.SakaiProject;
import gradingTools.sharedTestCase.CheckStyleTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;

public class VariableHasClassTypeTestCase extends CheckStyleTestCase {
	public VariableHasClassTypeTestCase(String aMessage) {
		super(aMessage);
	}
	public VariableHasClassTypeTestCase() {
		super("variable has class type");
	}
	public static final String WARNING_NAME = "variableHasClassType";
	@Override
	public String regexLineFilter() {
		// TODO Auto-generated method stub
		return ".*" + WARNING_NAME + ".*";
	}

	@Override
	public String failMessageSpecifier() {
		return null;
	}
	
	String beautify (String aCheckstyleString) {
		return aCheckstyleString.substring(aCheckstyleString.indexOf(WARNING_NAME)) + "\n";
	}
	String beautify (List<String> aList) {
		StringBuffer sb = new StringBuffer();
		for (String aString: aList) {
			String beautifiedString = beautify(aString);
			sb.append(beautifiedString);
			
		}
		return sb.toString();
	}
	
	protected  TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aMatchedLines, boolean autoGrade) {
		if (aMatchedLines.size() == 0) {
			return pass();
		}
		double score = Math.min(aMatchedLines.size(), 5) / 5.0;
        score = Math.max(0, 1 - score);
        return partialPass(score, beautify(aMatchedLines));
    	
    }
	 

}
