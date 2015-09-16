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

	@Override
	public String regexLineFilter() {
		// TODO Auto-generated method stub
		return ".*variableHasClassType.*";
	}

	@Override
	public String failMessageSpecifier() {
		return null;
	}
	
	protected  TestCaseResult test (SakaiProject aProject, String[] aCheckStyleLines, List<String> aMatchedLines, boolean autoGrade) {
		if (aMatchedLines.size() == 0) {
			return pass();
		}
		double score = Math.min(aMatchedLines.size(), 5) / 5.0;
        score = Math.max(0, 1 - score);
        return partialPass(score, aMatchedLines.toString());
    	
    }
	 

}
