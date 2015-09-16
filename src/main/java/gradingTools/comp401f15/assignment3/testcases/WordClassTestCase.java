package gradingTools.comp401f15.assignment3.testcases;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.misc.Common;
import wrappers.framework.project.ProjectWrapper;
import framework.execution.RunningProject;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.OutputAndErrorCheckingTestCase;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.execution.ResultWithOutput;
import grader.sakai.project.SakaiProject;
import grader.util.ExecutionUtil;

public class WordClassTestCase extends TokenScanningTestCase{

	public WordClassTestCase() {
        super("Word class test case");
    }
	

//	String[] beanDescriptions =  {null, "ScannerBean", ".*Bean.*", ".*Bean.*"};
//    Class[] constructorArgTypes2 = {String.class};
//    Class[] constructorArgTypes1 = {};
//    String[] constructorArgs2 = {""};
//    String[] constructorArgs1 = {};
    protected String inputEndingSpaces() { return  "CamelCase anotherWord wordWord ";}
    protected String input() { return "CamelCase anotherWord wordWord";}
    
   protected String[] expectedOutputs() {
	   Class aClassName =  (Class) this.getCheckable().getRequirements().getUserObject(WordTokenBeanTestCase.TAG);
	   String aPattern = ".*" + aClassName.getCanonicalName() + ".*";
	   return new String[] {aPattern, aPattern, aPattern };
	   
   };

    
}
