package gradingTools.sharedTestCase;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.sakai.project.SakaiProject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import wrappers.framework.project.ProjectWrapper;


public class MinDeclaredMethodsTestCase extends CheckStyleTestCase {
	int minimum;
	 public MinDeclaredMethodsTestCase(int aMinimum) {
	        super("Min declared method test case");
	        minimum = aMinimum;
	    }
    
	@Override
	public String regexLineFilter() {
		return "(.*)minDeclaredMethods(.*)";
	}



	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Number of declared methods less than" + minimum;
	}
  //String literal expressions should be on the left side
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
	    	return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
	    	
	}

}

