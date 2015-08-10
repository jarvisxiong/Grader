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

import wrappers.framework.project.ProjectWrapper;


public class EqualsAvoidNullTestCase extends CheckStyleTestCase {
	 public EqualsAvoidNullTestCase() {
	        super("Equal avoids null test case");
	    }
    
	@Override
	public String regexLineFilter() {
		return "(.*)String literal expressions should be on the left side(.*)";
	}



	@Override
	public String failMessageSpecifier() {
		// TODO Auto-generated method stub
		return "Literal should be target rather than argument of equals(Ignorecase)";
	}
  //String literal expressions should be on the left side

}

