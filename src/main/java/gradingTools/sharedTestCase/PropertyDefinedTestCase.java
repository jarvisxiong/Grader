package gradingTools.sharedTestCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;

public class PropertyDefinedTestCase extends MethodDefinedTestCase {
	

	public PropertyDefinedTestCase(String classTag, String propertyName,  Class<?> returnType) {
		super(classTag, "get" + propertyName, null, null, returnType);
//		CLASS_TAG = classTag;
//		METHOD_NAME = methodName;
//		METHOD_TAG = methodTag;
//		METHOD_REGEX = methodRegex;
//		RETURN_TYPE = returnType;
//		PARAMETER_TYPES = parameterTypes;
	}
	

	public PropertyDefinedTestCase(String classTag,String propertyName,  Object returnType) {
		super(classTag, "get" + propertyName, null, null, returnType);

	}
	
}
