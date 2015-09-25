package gradingTools.comp401f15.assignment1.testcases;

import java.util.List;

import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.execution.MainClassFinder;
import grader.sakai.project.SakaiProject;
import gradingTools.sharedTestCase.ClassDefinedTestCase;

public class MainClassDefinedTestCase extends ClassDefinedTestCase {

	public MainClassDefinedTestCase(String aDescriptor) {
		super(aDescriptor);
	}
	
	 protected TestCaseResult computeResult (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {
		 TestCaseResult aResult = singleMatchScore(aProject, aCheckStyleLines, aFailedLines, autoGrade);
		 if (aResult.getPercentage() != 1.0) {
			 if (aProject.getEntryPoints() == null || aProject.getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT) == null)
				 return aResult;
			 String aMainClassUsed = aProject.getEntryPoints().get(MainClassFinder.MAIN_ENTRY_POINT);
			 if (aMainClassUsed.matches(descriptor))
				 return pass();
			 if (aMainClassUsed.contains("main.") || aMainClassUsed.contains("Main.") ) {
				 return partialPass(0.5, aResult.getNotes() + " but main package defined or main package has wrong case");
			 }
		 }
		 return aResult;
	    	
	}

}
