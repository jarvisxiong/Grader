package gradingTools.comp401f15.assignment5.testcases;

import java.lang.reflect.Method;
import java.util.List;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;

public class FunctionExistsTestCase extends BasicTestCase {
	private String CLASS_TAG;
    private String[] CLASS_DESCRIPTIONS = new String[]{null, CLASS_TAG, ".*"+CLASS_TAG+".*", ".*"+CLASS_TAG+".*"};
    
    private String METHOD_NAME;
    private String METHOD_TAG;
    private String METHOD_REGEX;
    
    private String[] METHOD_DESCRIPTIONS = new String[]{METHOD_NAME, METHOD_TAG, METHOD_REGEX, ".*"+METHOD_TAG+".*"};
    
    
    private final Class<?> RETURN_TYPE;
    private final Class<?>[] PARAMETER_TYPES;

	public FunctionExistsTestCase(String classTag, String methodName, String methodTag, String methodRegex, Class<?> returnType, Class<?>... parameterTypes) {
		super("'" + methodName + "' method exists test case");
		
		CLASS_TAG = classTag;
		METHOD_NAME = methodName;
		METHOD_TAG = methodTag;
		METHOD_REGEX = methodRegex;
		RETURN_TYPE = returnType;
		PARAMETER_TYPES = parameterTypes;
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
		Class<?> clazz = IntrospectionUtil.findClass(project,
													 CLASS_DESCRIPTIONS[0],
													 CLASS_DESCRIPTIONS[1],
													 CLASS_DESCRIPTIONS[2],
													 CLASS_DESCRIPTIONS[3]);
		if (clazz == null) {
			return fail("Can't find '" + CLASS_TAG + "' class");
		}
		List<Method> methods = IntrospectionUtil.findMethod(clazz,
															METHOD_DESCRIPTIONS[0],
															METHOD_DESCRIPTIONS[1],
															METHOD_DESCRIPTIONS[2],
															METHOD_DESCRIPTIONS[3]);
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
			if (m.getReturnType().isAssignableFrom(RETURN_TYPE)) {
				curMatched[0] = true;
			} else if (m.getParameterCount() == PARAMETER_TYPES.length) {
				Class<?>[] mParameters = m.getParameterTypes();
				for(int i = 0; i < PARAMETER_TYPES.length; i ++) {
					if (!mParameters[i].isAssignableFrom(PARAMETER_TYPES[i])) {
						break;
					}
					curMatched[1] = true;
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
			String message = "'" + METHOD_NAME + "' method";
			if (METHOD_TAG != null) {
				message += " or method tagged '" + METHOD_TAG + "'";
			}
			message += " found, but without proper";
			if (!matched[0]) {
				message += "return type '" + RETURN_TYPE.getSimpleName() + "'";
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
