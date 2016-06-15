package gradingTools.comp401f15.assignment6.testcases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;

/**
 *
 * @author Andrew
 */
public class StandingAreaPlacementTestCase extends BasicTestCase {

    public StandingAreaPlacementTestCase() {
        super("Standing Area Placement Test Case");
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
        
        Method getKnightArea = null;
        Method[] getKnightX = null;
        Method getGuardArea = null;
        Method[] getGuardX = null;
        
        try {
            getKnightArea = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "KnightArea").get(0);
            getKnightX = MethodExecutionTestCase.recursiveFindMethod(getKnightArea.getReturnType(), "getX", "X");
            getGuardArea = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "GuardArea").get(0);
            getGuardX = MethodExecutionTestCase.recursiveFindMethod(getGuardArea.getReturnType(), "getX", "X");
        } catch (Exception e) {
            return fail("At least one of the following can't be found: getKnightArea, getGuardArea, KnightArea.getX, GuardArea.getX");
        }

        boolean result = checkLocations(bridgeSceneConstructor, getKnightArea, getGuardArea, getKnightX, getGuardX);
        
        if (result) {
            return pass();
        } else {
            return fail("GuardArea not right of KnightArea");
        }
    }
    
    private static boolean checkLocations(Constructor<?> bridgeSceneConstructor, Method getKnightArea, Method getGuardArea, Method getKnightX[], Method getGuardX[]) {
        boolean ret;
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(getKnightArea),                               // 0
            MethodEnvironment.get(getGuardArea),                                // 1
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getKnightX),  // 2
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getGuardX),   // 3
        };
        
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneConstructor, new Object[]{}, methods);
        System.err.println(Arrays.toString(exData));
        
        ret = checkLTValue(exData, 2, 3) == 0;
        
        System.out.println(ret);
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
        if (a >= results.length || b >= results.length) {
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
        if (results.length <= a || results.length <= b) {
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
