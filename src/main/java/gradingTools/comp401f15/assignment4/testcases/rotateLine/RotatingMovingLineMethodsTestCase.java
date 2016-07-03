package gradingTools.comp401f15.assignment4.testcases.rotateLine;

import java.lang.reflect.Method;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.Project;

/**
 *
 * @author Andrew Vitkus
 */
public class RotatingMovingLineMethodsTestCase extends BasicTestCase {
    private static final String TAG = "RotatingLine";
    private static final String[] classDescriptions = new String[]{null, TAG, ".*"+TAG+".*", ".*"+TAG+".*"};

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
            boolean[][] methodsPresent = new boolean[][]{{false, false, false}, {false, false, false}};
            for(Method m : aClass.getMethods()) {
                switch(m.getName()) {
                    case "setX":
                        methodsPresent[0][0] = true;
                        if(m.getReturnType().equals(void.class)) {
                            methodsPresent[0][1] = true;
                        }
                        if(m.getParameterCount() == 1 && m.getParameterTypes()[0].equals(int.class)) {
                            methodsPresent[0][2] = true;
                        }
                        break;
                    case "setY":
                        methodsPresent[1][0] = true;
                        if(m.getReturnType().equals(void.class)) {
                            methodsPresent[1][1] = true;
                        }
                        if(m.getParameterCount() == 1 && m.getParameterTypes()[0].equals(int.class)) {
                            methodsPresent[1][2] = true;
                        }
                        break;
                    default:
                }
            }
            int rawScore = getRawScoreFromBoolArray(methodsPresent);
            if (rawScore == 6) {
                return pass();
            } else if (rawScore == 0) {
                return fail(buildErrorMessageFromBoolArray(methodsPresent));
            } else {
                return partialPass(((double)rawScore)/6., buildErrorMessageFromBoolArray(methodsPresent));
            }
        }
    }
    
    public RotatingMovingLineMethodsTestCase() {
        super("Rotating Moving Line Class Methods Properly Defined Test Case");
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
        String[] methods = new String[]{"setX", "setY"};
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
        if (error.lastIndexOf("\n") == error.length() - 1) {
            error.setLength(error.length() - 1);
        }
        return error.toString();
    }
}
