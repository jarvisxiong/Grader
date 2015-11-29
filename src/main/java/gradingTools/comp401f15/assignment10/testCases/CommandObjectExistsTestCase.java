package gradingTools.comp401f15.assignment10.testCases;

import java.lang.reflect.Modifier;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;

public class CommandObjectExistsTestCase extends InterfaceImplementedTestCase {
	
	public CommandObjectExistsTestCase (String aTag) {
		super (aTag, Runnable.class);
		
	}

}
