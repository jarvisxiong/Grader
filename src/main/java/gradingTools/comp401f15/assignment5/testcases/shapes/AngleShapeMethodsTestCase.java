package gradingTools.comp401f15.assignment5.testcases.shapes;

import java.lang.reflect.Method;

import util.annotations.Tags;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;

/**
 *
 * @author Andrew Vitkus
 */
public class AngleShapeMethodsTestCase extends BasicTestCase {
    private static final String TAG = "AngleShape";
    private static final String[] classDescriptions = new String[]{null, TAG, ".*"+TAG+".*", ".*"+TAG+".*"};

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class<?> aClass = IntrospectionUtil.findClass(project, 
                            classDescriptions[0],
                            classDescriptions[1],
                            classDescriptions[2],
                            classDescriptions[3]);
        if (aClass == null) {
            return fail("Can't find angle shape class");
        } else {
            boolean[][] methodsPresent = new boolean[][]{{false, false, false, false}, {false, false, false, false}, {false, false, false, false}};
            for(Method m : aClass.getMethods()) {
                switch(m.getName()) {
                    case "getLeftLine":
                        methodsPresent[0][0] = true;
                        if(m.getReturnType().equals(Object.class)) {
                            methodsPresent[0][1] = true;
                        }
                        if(m.getParameterCount() == 0) {
                            methodsPresent[0][2] = true;
                        }
                        break;
                    case "getRightLine":
                        methodsPresent[1][0] = true;
                        if(m.getReturnType().equals(Object.class)) {
                            methodsPresent[1][1] = true;
                        }
                        if(m.getParameterCount() == 0) {
                            methodsPresent[1][2] = true;
                        }
                        break;
                    case "move":
                        methodsPresent[2][0] = true;
                        if(m.getReturnType().equals(void.class)) {
                            methodsPresent[2][1] = true;
                        }
                        if(m.getParameterCount() == 2) {
                            Class<?>[] parameterTypes = m.getParameterTypes();
                            methodsPresent[2][2] = parameterTypes[0].equals(int.class) && parameterTypes[1].equals(int.class);
                        }
                        break;
                    default:
                        if(m.getName().matches("((?![rR]ight).)*([lL]eft).*")) { // contains left but no right
                            methodsPresent[0][0] = true;
                            if(m.getReturnType().equals(Object.class)) {
                                methodsPresent[0][1] = true;
                            }
                            if(m.getParameterCount() == 0) {
                                methodsPresent[0][2] = true;
                            }
                        } else if(m.getName().matches("((?![lL]eft).)*([rR]ight).*")) { // contains right but no left
                            methodsPresent[0][0] = true;
                            if(m.getReturnType().equals(Object.class)) {
                                methodsPresent[0][1] = true;
                            }
                            if(m.getParameterCount() == 0) {
                                methodsPresent[0][2] = true;
                            }
                        } else {
                            boolean isNamedMoveMethod = m.getName().matches("((?![([rR]ight)([lL]eft)]).)*([mM]ove).*"); // move but not left or right
                            Tags methodTags = m.getAnnotation(Tags.class);
                            boolean isTaggedMoveMethod = methodTags != null && arrayContains(methodTags.value(), "move");
                            if(isNamedMoveMethod || isTaggedMoveMethod) {
                                methodsPresent[6][0] = true;
                                methodsPresent[6][1] = isTaggedMoveMethod;
                                if(m.getReturnType().equals(void.class)) {
                                    methodsPresent[6][2] = true;
                                }
                                if(m.getParameterCount() == 2) {
                                    Class<?>[] parameterTypes = m.getParameterTypes();
                                    methodsPresent[2][2] = parameterTypes[0].equals(int.class) && parameterTypes[1].equals(int.class);
                                }
                            }
                        }
                }
            }
            int rawScore = getRawScoreFromBoolArray(methodsPresent);
            if (rawScore == 10) {
                return pass();
            } else if (rawScore == 0) {
                return fail(buildErrorMessageFromBoolArray(methodsPresent));
            } else {
                return partialPass(((double)rawScore)/22., buildErrorMessageFromBoolArray(methodsPresent));
            }
        }
    }
    
    private static boolean arrayContains(Object[] arr, Object val) {
        if (arr == null) {
            return false;
        }
        for(Object o : arr) {
            if (o != null && val != null) {
                if (o.equals(val)) {
                    return true;
                }
            } else if (o == null && val == null) {
                return true;
            }
        }
        return false;
    }
    
    private static int getRawScoreFromBoolArray(boolean[][] marks) {
        int rawScore = 0;
        for (boolean[] part : marks) {
            for(int j = 0; j < part.length; j ++) {
                if(j == 0 && !part[j]) {
                    break;
                } else if (part[j]) {
                    rawScore ++;
                }
            }
        }
        return rawScore;
    }
    
    private static String buildErrorMessageFromBoolArray(boolean[][] marks) {
        String[] methods = new String[]{"getLeftLine", "getRightLine"};
        StringBuilder error = new StringBuilder();
        for(int i = 0; i < marks.length - 1; i ++) {
            boolean[] part = marks[i];
            if (!part[0]) {
                error.append("Missing method '").append(methods[i]).append("'\n");
            } else {
                if (!part[1]) {
                    error.append("Incorrect return type for method '").append(methods[i]).append("'\n");
                }
                if (!part[2]) {
                    error.append("Incorrect parameter count or types for method '").append(methods[i]).append("'\n");
                }
            }
        }
        boolean[] rotateMarks = marks[marks.length - 1];
        if (!rotateMarks[0]) {
            error.append("Missing method '").append(methods[methods.length - 1]).append("'\n");
        } else {
            if (!rotateMarks[1]) {
                error.append("Missing tag for method '").append(methods[methods.length - 1]).append("'\n");
            }
            if (!rotateMarks[2]) {
                error.append("Incorrect return type for method '").append(methods[methods.length - 1]).append("'\n");
            }
            if (!rotateMarks[3]) {
                error.append("Incorrect parameter count or types for method '").append(methods[methods.length - 1]).append("'\n");
            }
        }
        if (error.lastIndexOf("\n") == error.length() - 1) {
            error.setLength(error.length() - 1);
        }
        return error.toString();
    }
    
    public AngleShapeMethodsTestCase() {
        super("Angle Shape Methods Test Case");
    }
}
