package gradingTools.comp401f15.assignment4.testcases.rotateLine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.emory.mathcs.backport.java.util.Arrays;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.Project;

/**
 *
 * @author Andrew
 */
public class RotatingLineFunctionTestCase extends BasicTestCase {

    private static final String TAG = "RotatingLine";
    private static final String[] classDescriptions = new String[]{null, TAG, ".*"+TAG+".*", ".*"+TAG+".*"};
    private static final Map<Class<?>, Class<?>> primitiveTypeMap;
    
    static {
        primitiveTypeMap = new HashMap<>(18);
        primitiveTypeMap.put(int.class, Integer.class);
        primitiveTypeMap.put(long.class, Long.class);
        primitiveTypeMap.put(short.class, Short.class);
        primitiveTypeMap.put(float.class, Float.class);
        primitiveTypeMap.put(double.class, Double.class);
        primitiveTypeMap.put(char.class, Character.class);
        primitiveTypeMap.put(boolean.class, Boolean.class);
        primitiveTypeMap.put(void.class, Void.class);
        primitiveTypeMap.put(Integer.class, int.class);
        primitiveTypeMap.put(Long.class, long.class);
        primitiveTypeMap.put(Short.class, short.class);
        primitiveTypeMap.put(Float.class, float.class);
        primitiveTypeMap.put(Double.class, double.class);
        primitiveTypeMap.put(Character.class, char.class);
        primitiveTypeMap.put(Boolean.class, boolean.class);
        primitiveTypeMap.put(Void.class, void.class);
    }
    
    public RotatingLineFunctionTestCase() {
        super("Rotating Line Function Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class aClass = BasicProjectIntrospection.findClass(project, 
                            classDescriptions[0],
                            classDescriptions[1],
                            classDescriptions[2],
                            classDescriptions[3]);
        if (aClass == null) {
            return fail("Cannot find rotating line class");
        } else {
            Constructor lineConstructor = null;
            Constructor[] constructors = aClass.getConstructors();
            for(Constructor c : constructors) {
                if (c.getParameterCount() == 0) {
                    lineConstructor = c;
                }
            }
            int correct = 0;
            StringBuilder errors = new StringBuilder();
            try {
                Object line = lineConstructor.newInstance();
                errors.append(checkMethodSimple(aClass, line, "getX", null, int.class, null, 0)).append("\n");
                errors.append(checkMethodSimple(aClass, line, "getY", null, int.class, null, 0)).append("\n");
                errors.append(checkMethodSimple(aClass, line, "setAngle", new Class[]{double.class}, void.class, new Object[]{2.0}, null)).append("\n");
                errors.append(checkMethodSimple(aClass, line, "getAngle", null, double.class, null, 2.0)).append("\n");
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(RotatingLineFunctionTestCase.class.getName()).log(Level.SEVERE, null, ex);
            }
            return partialPass(0.5, "Missing default constructor");
        }
    }
    
    private static String checkMethodSimple(Class clazz, Object obj, String methodName, Class[] parameters, Class returnType, Object[] arguments, Object expectedValue) {
        if (parameters == null) {
            parameters = new Class[]{};
        }
        if (arguments == null) {
            arguments = new Object[]{};
        }
        Method m;
        try {
            m = clazz.getMethod(methodName, parameters);
        } catch (NoSuchMethodException | SecurityException ex) {
            String expectedParameters = "";
            for (Class c : parameters) {
                expectedParameters += c.getSimpleName() + ", ";
            }
            expectedParameters = expectedParameters.substring(0, expectedParameters.length() - 2);
            return "No method found for '"  + methodName + "' with expected parameters types (" + expectedParameters + ")";
        }
        Object ret;
        try {
            ret = m.invoke(obj, arguments);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return "Can't run method '" + methodName + "'";
        }
        if (expectedValue == null) {
            if (ret == null) {
                return "";
            }
        } else {
            if (expectedValue.equals(ret)) {
                return "";
            }
        }
        return "Incorect return from method '" + methodName + "'";
    }
    
    private static String checkMethod(Class clazz, Object obj, String methodName, Class[] parameters, Class returnType, Object[] arguments, Object expectedValue) {
        if (parameters == null) {
            parameters = new Class[]{};
        }
        if (arguments == null) {
            arguments = new Object[]{};
        }
        if (toNonPrimitiveClass(returnType).equals(Void.class)) {
            expectedValue = null;
        }
        List<Method> methods = BasicProjectIntrospection.findMethodByName(clazz, methodName);
        if (methods.isEmpty()) {
            return "No method found for '" + methodName + "'";
        }
        Method method = methods.get(0);
        Class[] methodParameters = method.getParameterTypes();
        if (methodParameters.length > 0 && parameters.length == 0) {
            return "No method found for '"  + methodName + "' with no parameters";
        }
        for(int i = 0; i < methodParameters.length; i ++) {
            Class methodParameterNonPrimative = toNonPrimitiveClass(methodParameters[i]);
            Class expectedParameterNonPrimative = toNonPrimitiveClass(parameters[i]);
            
            if (!methodParameterNonPrimative.isAssignableFrom(expectedParameterNonPrimative)) {
                String expectedParameters = "";
                for (Class c : parameters) {
                    expectedParameters += c.getSimpleName() + ", ";
                }
                expectedParameters = expectedParameters.substring(0, expectedParameters.length() - 2);
                return "No method found for '"  + methodName + "' with expected parameters types (" + expectedParameters + ")";
            }
        }
        Class methodReturnType = method.getReturnType();
        Class returnTypeNonPrimative = toNonPrimitiveClass(returnType);
        Class methodReturnTypeNonPrimative = toNonPrimitiveClass(methodReturnType);
        if (!returnTypeNonPrimative.isAssignableFrom(methodReturnTypeNonPrimative)) {
            return "No method found for '" + methodName + "' with expected return type " + returnType.getSimpleName();
        }
        Object returnValue = new RotatingLineFunctionTestCase(); // they will never return this class
        try {
            returnValue = method.invoke(obj, arguments);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return "Failed to call method '" + methodName + "'";
        }
        if (expectedValue == null) {
            if(returnValue == null) {
                return "";
            }
        } else {
            if (methodReturnType.isPrimitive() || returnType.isPrimitive()) {
                Class compareType = methodReturnType;
                Class nonPrimitiveExpectedReturnType = toNonPrimitiveClass(returnType);
                Class nonPrimitiveMethodReturnType = toNonPrimitiveClass(methodReturnType);
                if (nonPrimitiveExpectedReturnType != nonPrimitiveMethodReturnType) {
                    return "Missmatch between types of expected (" + returnType.getSimpleName() + ") and actual (" + methodReturnType.getSimpleName() + ") return types for method '" + methodName + "'";
                }
                Method compareMethod = null;
                try {
                    compareMethod = nonPrimitiveExpectedReturnType.getMethod("compare", compareType, compareType);
                    if (!compareMethod.getReturnType().equals(Boolean.TYPE)) {
                        compareMethod = null;
                    }
                } catch (NoSuchMethodException | SecurityException ex) { }
                if (compareMethod != null) {
                    try {
                        Boolean comparison = (Boolean)compareMethod.invoke(returnValue, expectedValue);
                        if (comparison) {
                            return "";
                        } else {
                            return "Method '" + methodName + "' did not behave as expected";
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) { }
                }
                return "Can't compre expected and actual return values of method '" + methodName + "'";
            } else {
                List<Method> equalsMethods = BasicProjectIntrospection.findMethodByName(clazz, "equals");
                Method equalsMethod = null;
                for(Method m : equalsMethods) {
                   if (Arrays.equals(m.getParameterTypes(), new Class[]{Object.class}) && m.getReturnType().equals(boolean.class)) {
                       equalsMethod = m;
                   }
                }
                if (equalsMethod == null || equalsMethod.getDeclaringClass().equals(Object.class)) {
                    return "Can't check return value of method '" + methodName + "' since return type doesn't override Object.equals(Object)";
                }
                if (expectedValue.equals(returnValue)) {
                    return "";
                }
            }
        }
        return "Method '" + methodName + "' did not behave as expected";
    }
    
    private static Class toNonPrimitiveClass(Class clazz) {
        if (clazz.isPrimitive()) {
            return primitiveTypeMap.get(clazz);
        } else {
            return clazz;
        }
    }
    
}
