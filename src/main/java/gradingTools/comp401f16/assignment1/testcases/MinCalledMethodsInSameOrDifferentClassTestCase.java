package gradingTools.comp401f16.assignment1.testcases;

import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.sakai.project.SakaiProject;
import gradingTools.sharedTestCase.MinCalledMethodsTestCase;
import wrappers.framework.project.ProjectWrapper;


public class MinCalledMethodsInSameOrDifferentClassTestCase extends MinCalledMethodsTestCase {
	 public MinCalledMethodsInSameOrDifferentClassTestCase(int aMinimum) {
	        super(aMinimum);
	    }
	  
	    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	        SakaiProject aProject = ((ProjectWrapper) project).getProject();
	        // need a more intelligent check than this
	        if (aProject.getClassesManager().getClassDescriptions().size() > 1)
	        	return pass();
	        return super.test(project, autoGrade);

	    }

}

