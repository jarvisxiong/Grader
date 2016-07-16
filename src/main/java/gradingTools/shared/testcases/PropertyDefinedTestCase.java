package gradingTools.shared.testcases;

public class PropertyDefinedTestCase extends MethodDefinedTestCase {
	

	public PropertyDefinedTestCase(String classTag, String propertyName,  Class<?> returnType) {
		super(classTag, "get" + propertyName, propertyName, null, returnType);
//		CLASS_TAG = classTag;
//		METHOD_NAME = methodName;
//		METHOD_TAG = methodTag;
//		METHOD_REGEX = methodRegex;
//		RETURN_TYPE = returnType;
//		PARAMETER_TYPES = parameterTypes;
	}
	

	public PropertyDefinedTestCase(String classTag,String propertyName,  Object returnType) {
		super(classTag, "get" + propertyName, propertyName, null, returnType);

	}
	
}
