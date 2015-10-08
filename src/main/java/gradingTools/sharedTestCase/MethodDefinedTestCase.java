package gradingTools.sharedTestCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import util.misc.Common;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;

public class MethodDefinedTestCase extends BasicTestCase {
	private String CLASS_TAG;
//    private String[] CLASS_DESCRIPTIONS = new String[]{null, CLASS_TAG, ".*"+CLASS_TAG+".*", ".*"+CLASS_TAG+".*"};
    
    private String METHOD_NAME;
    private String METHOD_TAG;
    private String METHOD_REGEX;
    
    private String[] METHOD_DESCRIPTIONS = new String[]{METHOD_NAME, METHOD_TAG, METHOD_REGEX, ".*"+METHOD_TAG+".*"};
  
    
    private  Class<?> RETURN_TYPE;
    private  Class<?>[] PARAMETER_TYPES;
    Object returnObject;
    Object[] parameterObjects;

	public MethodDefinedTestCase(String classTag, String methodName, String methodTag, String methodRegex, Class<?> returnType, Class<?>... parameterTypes) {
		super("'" + methodName + "' method exists test case");
		init(classTag, methodName, methodTag, methodRegex, returnType, parameterTypes);
//		CLASS_TAG = classTag;
//		METHOD_NAME = methodName;
//		METHOD_TAG = methodTag;
//		METHOD_REGEX = methodRegex;
//		RETURN_TYPE = returnType;
//		PARAMETER_TYPES = parameterTypes;
	}
	
	public void init(String classTag, String methodName, String methodTag, String methodRegex, Class<?> returnType, Class<?>... parameterTypes) {
		
		CLASS_TAG = classTag;
		METHOD_NAME = methodName;
		METHOD_TAG = methodTag;
		METHOD_REGEX = methodRegex;
		RETURN_TYPE = returnType;
		PARAMETER_TYPES = parameterTypes;
		
	}
	public MethodDefinedTestCase(String classTag, String methodName, String methodTag, String methodRegex, Object returnType, Object... parameterTypes) {
		super("'" + methodName + "' method exists test case");
		init(classTag, methodName, methodTag, methodRegex, null, null);
		returnObject = returnType;
		parameterObjects = parameterTypes;
//		CLASS_TAG = classTag;
//		METHOD_NAME = methodName;
//		METHOD_TAG = methodTag;
//		METHOD_REGEX = methodRegex;
//		RETURN_TYPE = returnType;
//		PARAMETER_TYPES = parameterTypes;
	}
	public MethodDefinedTestCase(String classTag,String methodTag,  Object returnType, Object... parameterTypes) {
		super("'" + methodTag + "' method exists test case");
		init(classTag, methodTag, null, null, null, null);
		returnObject = returnType;
		parameterObjects = parameterTypes;
//		CLASS_TAG = classTag;
//		METHOD_NAME = methodName;
//		METHOD_TAG = methodTag;
//		METHOD_REGEX = methodRegex;
//		RETURN_TYPE = returnType;
//		PARAMETER_TYPES = parameterTypes;
	}
	Class toClass (Project aProject, Object aClass) {
		if (aClass instanceof Class) {
			return (Class) aClass;
		}
		if (aClass instanceof String) {
			Class retVal = IntrospectionUtil.getOrFindInterface(aProject, this, (String) aClass);
			if (retVal != null)
				return retVal;
			return IntrospectionUtil.getOrFindClass(aProject, this, (String) aClass);
			
			
		}
		return null;
	}
	Class[] toClass(Project aProject, Object[] aClasses) {
		List<Class> result = new ArrayList();
		for (Object aClass:aClasses) {
			result.add(toClass(aProject, aClass));
		}
		return result.toArray(new Class[result.size()]);
		
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
		if (RETURN_TYPE == null || returnObject != null ) {
			RETURN_TYPE = toClass(project, returnObject);
		}
		if (PARAMETER_TYPES == null || parameterObjects != null) {
			PARAMETER_TYPES = toClass(project, parameterObjects);
		}
//		Class<?> clazz = IntrospectionUtil.findClass(project,
//													 CLASS_DESCRIPTIONS[0],
//													 CLASS_DESCRIPTIONS[1],
//													 CLASS_DESCRIPTIONS[2],
//													 CLASS_DESCRIPTIONS[3]);
		Class<?> clazz = IntrospectionUtil.getOrFindClass(project, this, CLASS_TAG);
//		if (clazz == null)
//			clazz = IntrospectionUtil.getOrFindClass(project, this, CLASS_TAG);
		if (clazz == null) {
			return fail("Can't find '" + CLASS_TAG + "' class");
		}
		if (RETURN_TYPE == null) {
			return fail("Can't find '" + returnObject + "' class");
		}
		if (PARAMETER_TYPES == null) {
			return fail("Can't find '" + Common.toString(parameterObjects) + "' class");
		}

//		List<Method> methods = IntrospectionUtil.findMethod(clazz,
//															METHOD_DESCRIPTIONS[0],
//															METHOD_DESCRIPTIONS[1],
//															METHOD_DESCRIPTIONS[2],
//															METHOD_DESCRIPTIONS[3]);
//		List<Method> methods = IntrospectionUtil.findMethod(aJavaClass, aName, aTag, aNameMatch, aTagMatch)(clazz,
//				METHOD_DESCRIPTIONS[0],
//				METHOD_DESCRIPTIONS[1],
//				METHOD_DESCRIPTIONS[2],
//				METHOD_DESCRIPTIONS[3]);
		List<Method> methods = IntrospectionUtil.getOrFindMethods(project, this, clazz, METHOD_NAME);
		if (methods.isEmpty()) {
			String message = "Can't find '" + METHOD_NAME + "' method";
			if (METHOD_TAG != null) {
				message += " or method tagged '" + METHOD_TAG + "'";
			}
			message += " in class '" + clazz.getSimpleName() + "'";
			return fail(message);
		}
		Method method = null;
		boolean[] matched = new boolean[]{false, false};
		int matchedCount = 0;
		for(Method m : methods) {
			boolean[] curMatched = new boolean[]{false, false};
			if (RETURN_TYPE.isAssignableFrom(m.getReturnType()) ||
					m.getReturnType().isAssignableFrom(RETURN_TYPE)	// in case intyerface was not tagged correctly
					) {
				curMatched[0] = true;
			} 
			if (m.getParameterCount() == PARAMETER_TYPES.length) {
				curMatched[1] = true;

				Class<?>[] mParameters = m.getParameterTypes();
				for(int i = 0; i < PARAMETER_TYPES.length; i ++) {
					if (!mParameters[i].isAssignableFrom(PARAMETER_TYPES[i])) {
						curMatched[1] = false;
//						method = m;
						break;
					}
				}
				int curMatchedCount = countOf(curMatched, true);
				if (curMatchedCount == 2) {
					method = m;
					break;
				} else if (curMatchedCount > matchedCount) {
					matched = curMatched;
					matchedCount = curMatchedCount;
				}
			}
		}
		if (method == null) {
			String message = "'" + METHOD_NAME + "' method(s)";
			if (METHOD_TAG != null) {
				message += " or method(s) tagged '" + METHOD_TAG + "'";
			}
			message += " found" + " (" + methods + ")" + ", but without proper";
			if (!matched[0]) {
				message += "return type '" + RETURN_TYPE.getSimpleName() + "'"; //+ " actual type is:" + method.getReturnType().getSimpleName();
			}
			if (!matched[1]) {
				if (!matched[0]) {
					message += " nor";
				}
				message += " parameters " + buildParameterString();
			}
			message += " in class '" + clazz.getSimpleName() + "'";
			return partialPass(0.25, message);
		}
		IntrospectionUtil.putMethod(project, this, clazz, METHOD_NAME, method);
		return pass();
	}
	
	private static int countOf(boolean[] arr, boolean value) {
		int count = 0;
		for(boolean bool : arr) {
			count += bool ? 1 : 0;
		}
		return count;
	}

	private String buildParameterString() {
		StringBuilder retVal = new StringBuilder();
		retVal.append("(");
		for(int i = 0; i < PARAMETER_TYPES.length; i++) {
			Class<?> type = PARAMETER_TYPES[i];
			if (i != 0) {
				retVal.append(", ");
			}
			retVal.append("'" + type.getSimpleName() + "'");
		}
		retVal.append(")");
		return retVal.toString();
	}
}
