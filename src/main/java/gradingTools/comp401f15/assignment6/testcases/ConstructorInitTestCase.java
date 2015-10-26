package gradingTools.comp401f15.assignment6.testcases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodReturnReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Andrew
 */
public class ConstructorInitTestCase extends BasicTestCase {

    public ConstructorInitTestCase() {
        super("Avatar Initialization in Constructor Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class bridgeSceneClass = IntrospectionUtil.getOrFindClass(project, this, "BridgeScene");

        Constructor<?> bridgeSceneConstructor;
        try {
            bridgeSceneConstructor = bridgeSceneClass.getConstructor();
        } catch (NoSuchMethodException | SecurityException ex) {
            return fail("Can't access BridgeScene constructor");
        }
        
        if (bridgeSceneConstructor == null) {
            return fail("Can't find empty BridgeScene constructor");
        }
        
        Method getArthur = null;
        Method getLancelot = null;
        Method getRobin = null;
        Method getGalahad = null;
        Method getGuard = null;
        Method[] getAvatarX = new Method[2];
        
        Method getKnightArea = null;
        Method[] getKnightX = null;
        Method getGuardArea = null;
        Method[] getGuardX = null;
        
        try {
            getArthur = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Arthur").get(0);
            getLancelot = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Lancelot").get(0);
            getRobin = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Robin").get(0);
            getGalahad = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Galahad").get(0);
            List<Method> guardMethods = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Guard");
            for(Method m : guardMethods) {
                if (m.getName().matches(".*[aA]rea.*")) {
                    getGuardArea = m;
                } else {
                    getGuard = m;
                }
            }
            if (getGuard == null || getGuardArea == null) {
                throw new Exception();
            }
            getAvatarX[0] = IntrospectionUtil.getOrFindMethodList(project, this, getArthur.getReturnType(), "Head").get(0);
            getAvatarX[1] = IntrospectionUtil.getOrFindMethodList(project, this, getAvatarX[0].getReturnType(), "getX").get(0);
            
            getKnightArea = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "KnightArea").get(0);
            getKnightX = MethodExecutionTestCase.recursiveFindMethod(getKnightArea.getReturnType(), "getX", "X");
            //getGuardArea = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "GuardArea").get(0);
            getGuardX = MethodExecutionTestCase.recursiveFindMethod(getGuardArea.getReturnType(), "getX", "X");
        } catch (Exception e) {
            return fail("At least one of the following can't be found: getArthur, getLancelot, getRobin, getGalahad, getGuard, getKnightArea, getGuardArea, Avatar.getX, KnightArea.getX, GuardArea.getX");
        }

        boolean[] results = checkConstructor(bridgeSceneConstructor, getArthur, getLancelot, getRobin, getGalahad, getGuard, getKnightArea, getGuardArea, getAvatarX, getKnightX, getGuardX);
        
        int correct = count(results, true);
        int possible = results.length;
        if (correct == 0) {
            return fail("Completley incorrect initalization");
        } else if (correct == possible) {
            return pass();
        } else {
            double score = ((double)correct) / possible;
            String message = buildMessage(results);
            return partialPass(score, message);
        }
    }
    
    private static boolean[] checkConstructor(Constructor<?> bridgeSceneConstructor, Method getArthur, Method getLancelot, Method getRobin, Method getGalahad, Method getGuard, Method getKnightArea, Method getGuardArea, Method[] getAvatarX, Method[] getKnightX, Method[] getGuardX) {
        boolean[] ret = new boolean[12];
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(getArthur),                                   // 0
            MethodEnvironment.get(getLancelot),                                 // 1
            MethodEnvironment.get(getRobin),                                    // 2
            MethodEnvironment.get(getGalahad),                                  // 3
            MethodEnvironment.get(getGuard),                                    // 4
            MethodEnvironment.get(getKnightArea),                               // 5
            MethodEnvironment.get(getGuardArea),                                // 6
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getAvatarX),  // 7
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getAvatarX),  // 8
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getAvatarX),  // 9
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M3_RET, getAvatarX),  // 10
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, new MethodReturnReference(4), getAvatarX),    // 11
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, new MethodReturnReference(5), getKnightX),    // 12
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, new MethodReturnReference(6), getGuardX),     // 13
        };
        
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneConstructor, new Object[]{}, methods);
        System.err.println(Arrays.toString(exData));
        
        ret[0] = checkNEqual(exData, 0, null);
        ret[1] = checkNEqual(exData, 1, null);
        ret[2] = checkNEqual(exData, 2, null);
        ret[3] = checkNEqual(exData, 3, null);
        ret[4] = checkNEqual(exData, 4, null);
        ret[5] = checkNEqual(exData, 5, null);
        ret[6] = checkNEqual(exData, 6, null);
        ret[7] = checkLTValue(exData, 7, 12) == 0;
        ret[8] = checkLTValue(exData, 8, 12) == 0;
        ret[9] = checkLTValue(exData, 9, 12) == 0;
        ret[10] = checkLTValue(exData, 10, 12) == 0;
        ret[11] = checkGTValue(exData, 11, 12) == 0;
        
        System.out.println(Arrays.toString(ret));
        return ret;
    }
    
    private static boolean checkCorrectAction(Object[] results, int original, int now, int occupied, int knightTurn, boolean checkKnight) {
        if (occupied >= results.length || knightTurn >= results.length) {
            return false;
        }
        Object oOccupied = results[occupied];
        Object oKnightTurn = results[knightTurn];
        if (oOccupied instanceof Exception || oKnightTurn instanceof Exception) {
            return false; // error in execution
        }
        if (!(oOccupied instanceof Boolean) || !(oKnightTurn instanceof Boolean)) {
            return false; // not boolean
        }
        boolean bOccupied = (Boolean)oOccupied;
        boolean bKnightTurn = (Boolean)oKnightTurn;
        boolean same = checkEqual(results, original, now);
        return Boolean.compare(same, bOccupied && (Boolean.compare(bKnightTurn, checkKnight) == 0)) == 0;
    }
    
    private static boolean checkIs(Object[] results, int a, boolean b) {
        return a < results.length && (results[a] instanceof Boolean) && ((Boolean)results[a]) == b;
    }
    
    private static boolean checkNotIntance(Object[] results, int a, Class<?> c) {
        return a < results.length && !c.isInstance(results[a]);
    }
    
    private static boolean checkInstance(Object[] results, int a, Class<?> c) {
        return a < results.length && c.isInstance(results[a]);
    }
    
    private static boolean checkNEqual(Object[] results, int a, Object value) {
        return !checkEqual(results, a, value);
    }
    
    private static boolean checkEqual(Object[] results, int a, Object value) {
        if (a < results.length) {
            return false;
        }
        
        return Objects.equals(results[a], value);
    }
    
    private static boolean checkNEqual(Object[] results, int a, int b) {
        return !checkEqual(results, a, b);
    }
    
    private static boolean checkEqual(Object[] results, int a, int b) {
        if (a >= results.length || b > results.length) {
            return false;
        }
        Object oA = results[a];
        Object oB = results[b];
        if ((oA instanceof Exception) || (oB instanceof Exception)) {
            return false;
        }
        return Objects.equals(oA, oB);
    }
    
    
    private static int checkEqualValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, 0);
    }
    
    private static int checkLTValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, -1);
    }
    
    private static int checkGTValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, 1);
    }
    
    private static int checkCompare(Object[] results, int a, int b, int sign) {
        if (results.length < a || results.length < b) {
            return 1; // checking out of bounds
        }
        Object oA = results[a];
        Object oB = results[b];
        if (oA instanceof Exception || oB instanceof Exception) {
            return 2; // error in execution
        }
        if (!(oA instanceof Comparable) || !(oB instanceof Comparable)) {
            return 3; // not comparable
        }
        Comparable cA = (Comparable)oA;
        Comparable cB = (Comparable)oB;
        if (Math.signum(cA.compareTo(cB)) != sign) {
            return 4; // check comparison
        }
        
        return 0;
    }
    
    private static String buildMessage(boolean[] notes) {
        StringBuilder ret = new StringBuilder();
        if (notes[0] == false) {
            ret.append("Arthur not initalized in constructor\n");
        }
        if (notes[1] == false) {
            ret.append("Lancelot not initalized in constructor\n");
        }
        if (notes[2] == false) {
            ret.append("Robin not initalized in constructor\n");
        }
        if (notes[3] == false) {
            ret.append("Galahad not initalized in constructor\n");
        }
        if (notes[4] == false) {
            ret.append("Guard not initalized in constructor\n");
        }
        if (notes[5] == false) {
            ret.append("KnightArea not initalized in constructor\n");
        }
        if (notes[6] == false) {
            ret.append("GuardArea not initalized in constructor\n");
        }
        if (notes[7] == false) {
            ret.append("Arthur not initalized left of KnightArea\n");
        }
        if (notes[8] == false) {
            ret.append("Lancelot not initalized left of KnightArea\n");
        }
        if (notes[9] == false) {
            ret.append("Robin not initalized left of KnightArea\n");
        }
        if (notes[10] == false) {
            ret.append("Galahad not initalized left of KnightArea\n");
        }
        if (notes[11] == false) {
            ret.append("Guard not initalized right of KnightArea\n");
        }
        return ret.toString();
    }
    
    private static int count(boolean[] arr, boolean value) {
        int count = 0;
        for(boolean bool : arr) {
            if (bool == value) {
                count ++;
            }
        }
        return count;
    }
}
