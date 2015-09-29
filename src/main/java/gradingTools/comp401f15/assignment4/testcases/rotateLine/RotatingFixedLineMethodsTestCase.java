package gradingTools.comp401f15.assignment4.testcases.rotateLine;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;
import java.lang.reflect.Method;
import util.annotations.Tags;

/**
 *
 * @author Andrew Vitkus
 */
public class RotatingFixedLineMethodsTestCase extends BasicTestCase {
    private static final String TAG = "RotatingLine";
    private static final String[] classDescriptions = new String[]{null, TAG, ".*"+TAG+".*", ".*"+TAG+".*"};

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class aClass = IntrospectionUtil.findClass(project, 
                            classDescriptions[0],
                            classDescriptions[1],
                            classDescriptions[2],
                            classDescriptions[3]);
        if (aClass == null) {
            return fail("Cannot find rotating line class");
        } else {
            boolean[][] methodsPresent = new boolean[][]{{false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false}, {false, false, false, false}};
            for(Method m : aClass.getMethods()) {
                switch(m.getName()) {
                    case "getX":
                        methodsPresent[0][0] = true;
                        if(m.getReturnType().equals(int.class)) {
                            methodsPresent[0][1] = true;
                        }
                        if(m.getParameterCount() == 0) {
                            methodsPresent[0][2] = true;
                        }
                        break;
                    case "getY":
                        methodsPresent[1][0] = true;
                        if(m.getReturnType().equals(int.class)) {
                            methodsPresent[1][1] = true;
                        }
                        if(m.getParameterCount() == 0) {
                            methodsPresent[1][2] = true;
                        }
                        break;
                    case "getWidth":
                        methodsPresent[2][0] = true;
                        if(m.getReturnType().equals(int.class)) {
                            methodsPresent[2][1] = true;
                        }
                        if(m.getParameterCount() == 0) {
                            methodsPresent[2][2] = true;
                        }
                        break;
                    case "getHeight":
                        methodsPresent[3][0] = true;
                        if(m.getReturnType().equals(int.class)) {
                            methodsPresent[3][1] = true;
                        }
                        if(m.getParameterCount() == 0) {
                            methodsPresent[3][2] = true;
                        }
                        break;
                    default:
                        if(m.getName().matches("set.*[aA]ngle.*")) {
                            methodsPresent[4][0] = true;
                            if(m.getReturnType().equals(void.class)) {
                                methodsPresent[4][1] = true;
                            }
                            if(m.getParameterCount() == 1 && m.getParameterTypes()[0].equals(double.class)) {
                                methodsPresent[4][2] = true;
                            }
                        } else if(m.getName().matches("set.*[rR]adius.*")) {
                            methodsPresent[5][0] = true;
                            if(m.getReturnType().equals(void.class)) {
                                methodsPresent[5][1] = true;
                            }
                            if(m.getParameterCount() == 1 && m.getParameterTypes()[0].equals(double.class)) {
                                methodsPresent[5][2] = true;
                            }
                        } else if(m.getName().matches(".*[rR]otate.*") || arrayContains(m.getAnnotation(Tags.class).value(), "rotate")) {
                            methodsPresent[5][0] = true;
                            methodsPresent[5][1] = arrayContains(m.getAnnotation(Tags.class).value(), "rotate");
                            if(m.getReturnType().equals(void.class)) {
                                methodsPresent[5][2] = true;
                            }
                            if(m.getParameterCount() == 1 && m.getParameterTypes()[0].equals(int.class)) {
                                methodsPresent[5][3] = true;
                            }
                        }
                }
            }
            int rawScore = getRawScoreFromBoolArray(methodsPresent);
            if (rawScore == 22) {
                return pass();
            } else if (rawScore == 0) {
                return fail(buildErrorMessageFromBoolArray(methodsPresent));
            } else {
                return partialPass(((double)rawScore)/22., buildErrorMessageFromBoolArray(methodsPresent));
            }
        }
    }
    
    public RotatingFixedLineMethodsTestCase() {
        super("Rotating Fixed Line Class Methods Properly Defined Test Case");
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
        String[] methods = new String[]{"getX", "getY", "getWidth", "getHeight", "setAngle", "setRadius", "rotate"};
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
}
