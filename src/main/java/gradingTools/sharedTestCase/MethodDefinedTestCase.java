package gradingTools.sharedTestCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.misc.Common;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ProjectIntrospection;

public class MethodDefinedTestCase extends BasicTestCase {
	private String CLASS_TAG;
//    private String[] CLASS_DESCRIPTIONS = new String[]{null, CLASS_TAG, ".*"+CLASS_TAG+".*", ".*"+CLASS_TAG+".*"};
    
    private String METHOD_NAME;
    private String METHOD_TAG;
    private String METHOD_REGEX;
    
    private String[] METHOD_DESCRIPTIONS = new String[]{METHOD_NAME, METHOD_TAG, METHOD_REGEX, ".*"+METHOD_TAG+".*"};
  
    
    private  Class<?>[] RETURN_TYPE;
    private  Class<?>[][] PARAMETER_TYPES;
    Object returnObject;
    Object[] parameterObjects;
    protected Method foundMethod;

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
                if (returnType == null) {
                    RETURN_TYPE = null;
                } else {
                    RETURN_TYPE = new Class<?>[]{returnType};
                }
                if(parameterTypes == null) {
                    PARAMETER_TYPES = null;
                } else {
                    PARAMETER_TYPES = Arrays.stream(parameterTypes).map((c) -> new Class[]{c}).toArray(Class[][]::new);
                }
                
		//PARAMETER_TYPES = parameterTypes;
		
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
		init(classTag, methodTag, methodTag, null, null, null);
		returnObject = returnType;
		parameterObjects = parameterTypes;
//		CLASS_TAG = classTag;
//		METHOD_NAME = methodName;
//		METHOD_TAG = methodTag;
//		METHOD_REGEX = methodRegex;
//		RETURN_TYPE = returnType;
//		PARAMETER_TYPES = parameterTypes;
	}
	Class[] toClasses (Project aProject, Object aClass) {
		if (aClass instanceof Class) {
			return new Class[]{(Class)aClass};
		}
		if (aClass instanceof String) {
			List<Class> retVal = ProjectIntrospection.getOrFindInterfaces(aProject, this, (String) aClass);
			if (retVal == null || retVal.isEmpty()) {
                            retVal = ProjectIntrospection.getOrFindClasses(aProject, this, (String) aClass);
                        }
			return retVal.toArray(new Class[retVal.size()]);
			
		}
		return null;
	}
	Class[][] toClasses(Project aProject, Object[] aClasses) {
		List<Class[]> result = new ArrayList();
		for (Object aClass:aClasses) {
			result.add(toClasses(aProject, aClass));
		}
                int maxSize = result.stream().mapToInt((cList) -> cList.length).max().orElse(0);
		return result.toArray(new Class[result.size()][maxSize]);
		
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
	System.err.println("PARAMETERS: " + Arrays.toString(PARAMETER_TYPES));
        System.err.println("RETURN: " + Arrays.toString(RETURN_TYPE));
            if (RETURN_TYPE == null || returnObject != null ) {
			RETURN_TYPE = toClasses(project, returnObject);
		}
		if (PARAMETER_TYPES == null || parameterObjects != null) {
			PARAMETER_TYPES = toClasses(project, parameterObjects);
		}
        System.err.println("PARAMETERS: " + Arrays.toString(PARAMETER_TYPES));
        System.err.println("RETURN: " + Arrays.toString(RETURN_TYPE));
//		Class<?> clazz = IntrospectionUtil.findClass(project,
//													 CLASS_DESCRIPTIONS[0],
//													 CLASS_DESCRIPTIONS[1],
//													 CLASS_DESCRIPTIONS[2],
//													 CLASS_DESCRIPTIONS[3]);
		Class<?> clazz = ProjectIntrospection.getOrFindClass(project, this, CLASS_TAG);
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
		List<Method> methods = ProjectIntrospection.getOrFindMethodList(project, this, clazz, METHOD_NAME, METHOD_TAG);
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
			if (isAssignableFromAny(m.getReturnType(), RETURN_TYPE)	// in case intyerface was not tagged correctly
					) {
				curMatched[0] = true;
			} 
			if (m.getParameterCount() == PARAMETER_TYPES.length) {
				curMatched[1] = true;

				Class<?>[] mParameters = m.getParameterTypes();
				for(int i = 0; i < PARAMETER_TYPES.length; i ++) {
					if (!isAssignableFromAny(mParameters[i], PARAMETER_TYPES[i])) {
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
				message += " return type '" + buildReturnString() + "'"; //+ " actual type is:" + method.getReturnType().getSimpleName();
			}
			if (!matched[1]) {
				if (!matched[0]) {
					message += " nor";
				}
				message += " parameters " + buildParameterString();
			}
			message += " in class '" + clazz.getSimpleName() + "'";
                        double score = matchedCount == 0 ? 0.25 : 0.5;
			return partialPass(score, message);
                        
		}
		ProjectIntrospection.putMethod(project, this, clazz, METHOD_NAME, method);
		foundMethod = method;
		return pass();
	}
        
        private static boolean isAssignableFromAny(Class<?> a, Class<?>[] b) {
            for(Class<?> c : b) {
                if (a.isAssignableFrom(c) || c.isAssignableFrom(a)) {
                    return true;
                }
            }
            return false;
        }
        
        private static boolean isAssignableFromAny(Class<?>[] a, Class<?>[] b) {
            for(Class<?> c : a) {
                if (isAssignableFromAny(c, b)) {
                    return true;
                }
            }
            return false;
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
		if (PARAMETER_TYPES.length == 0) {
			retVal.append(Common.toString(parameterObjects));
		} else {
		for(int i = 0; i < PARAMETER_TYPES.length; i++) {
			if (i != 0) {
				retVal.append(", ");
			}
                        retVal.append("(");
			Class<?>[] types = PARAMETER_TYPES[i];
						if (types.length == 0) {
							retVal.append(parameterObjects[i]);
						} else {
                        for(int j = 0; j < types.length; j ++) {
                            if (j != 0) {
				retVal.append(", ");
                            }
                            Class<?> type = types[j];
                            retVal.append("'").append(type.getSimpleName()).append("'");
                        }
						}
                        retVal.append(")");
		}
		}
		retVal.append(")");
		return retVal.toString();
	}
        
        private String buildReturnString() {
		StringBuilder retVal = new StringBuilder();
		retVal.append("(");
		if (RETURN_TYPE.length == 0) {
			retVal.append(returnObject);
		} else {
		for(int i = 0; i < RETURN_TYPE.length; i++) {
			if (i != 0) {
				retVal.append(", ");
			}
                        Class<?> type = RETURN_TYPE[i];
                        retVal.append("'").append(type.getSimpleName()).append("'");
		}
		}
		retVal.append(")");
		return retVal.toString();
	}
}
