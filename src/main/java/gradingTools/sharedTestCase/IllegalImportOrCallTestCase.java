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


public class IllegalImportOrCallTestCase extends CheckStyleTestCase {
	 public IllegalImportOrCallTestCase() {
	        super("Illegal import or call test case");
	    }
    
	@Override
	public String regexLineFilter() {
		return "(.*)illegalTypeImported(.*) | (.*)illegalMethodCall(.*)";
	}



	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Illegal type or call";
	}
  //String literal expressions should be on the left side
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
	    	return singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
	    	
	}

}

