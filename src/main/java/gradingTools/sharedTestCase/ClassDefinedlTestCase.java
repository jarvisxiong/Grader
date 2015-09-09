package gradingTools.sharedTestCase;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.execution.MainClassFinder;
import grader.sakai.project.SakaiProject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import wrappers.framework.project.ProjectWrapper;


public class ClassDefinedlTestCase extends CheckStyleTestCase {
	 String descriptor;
	 public ClassDefinedlTestCase(String aDescriptor) {
	        super(aDescriptor + " defined");
	        descriptor = aDescriptor;
	  }
	 protected boolean failOnMatch() {
	    	return false;
	    }
    
	@Override
	public String regexLineFilter() {
		return "(.*)Class matching " + descriptor + " defined(.*)";
	}



	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Class matching " + descriptor + " not defined";
	}
  //String literal expressions should be on the left side
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
		 TestCaseResult aResult = singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
		 if (aResult.getPercentage() != 1.0) {
			 if (aProject.getEntryPoints() == null || aProject.getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT) == null)
				 return aResult;
			 String aMainClassUsed = aProject.getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT);
			 if (aMainClassUsed.contains("main.") || aMainClassUsed.contains("Main.") ) {
				 return partialPass(0.5, aResult.getNotes() + " but main package defined or main package has wrong case");
			 }
		 }
		 return aResult;
	    	
	}

}

