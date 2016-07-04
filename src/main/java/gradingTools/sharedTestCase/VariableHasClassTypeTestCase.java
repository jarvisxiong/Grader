package gradingTools.sharedTestCase;

import grader.basics.junit.TestCaseResult;
import grader.sakai.project.SakaiProject;

import java.util.List;

public class VariableHasClassTypeTestCase extends CheckStyleTestCase {
	public VariableHasClassTypeTestCase(String aMessage) {
		super(null, aMessage);
	}
	public VariableHasClassTypeTestCase() {
		super(null, "variable has class type");
	}
	public static final String WARNING_NAME = "variableHasClassType";
	@Override
	public String regexLineFilter() {
		// TODO Auto-generated method stub
		return ".*" + WARNING_NAME + ".*";
	}

	@Override
	public String failMessageSpecifier() {
		return "Variable has class type failed";
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
