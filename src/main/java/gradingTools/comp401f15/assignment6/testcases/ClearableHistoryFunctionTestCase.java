package gradingTools.comp401f15.assignment6.testcases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCase;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;
import grader.util.ProjectIntrospection;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;

/**
 *
 * @author Andrew
 */
public class ClearableHistoryFunctionTestCase extends BasicTestCase {

    public static final String CLEARABLE_HISTORY = "ClearableHistory";
    
    public ClearableHistoryFunctionTestCase() {
        super("ClearableHistory function Test Case");
    }
    
    public static void locateClearableHistory(Project p, TestCase testCase) {
        Class scannerBeanClass = ProjectIntrospection.getOrFindClass(p, testCase, "ScannerBean");
        locateClearableHistory(p, testCase, scannerBeanClass);
    }
    
    public static void locateClearableHistory(Project p, TestCase testCase, Class<?> scannerBeanClass) {
        try {
            Method getTokenList = ProjectIntrospection.getOrFindMethodList(p, testCase, scannerBeanClass, "TokenList").get(0);
            testCase.getCheckable().getRequirements().putUserObject(CLEARABLE_HISTORY, getTokenList);
        } catch (Exception e) { }
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class scannerBeanClass = ProjectIntrospection.getOrFindClass(project, this, "ScannerBean");

        Constructor<?> scannerBeanConstructor;
        try {
            scannerBeanConstructor = scannerBeanClass.getConstructor();
        } catch (NoSuchMethodException | SecurityException ex) {
            return fail("Can't access ScannerBean constructor");
        }
        
        if (scannerBeanConstructor == null) {
            return fail("Can't find empty ScannerBean constructor");
        }
        
        Method setString = null;
        Method getTokenList = null;
        Method clear = null;
        Method getSize = null;
        
        locateClearableHistory(project, this, scannerBeanClass);
        try {
            setString = ProjectIntrospection.getOrFindMethodList(project, this, scannerBeanClass, "String").stream().filter((m)->m.getName().matches(".*set.*")).findAny().get();
            getTokenList = ProjectIntrospection.getOrFindMethodList(project, this, scannerBeanClass, "TokenList").get(0);
            clear = ProjectIntrospection.getOrFindMethodList(project, this, getTokenList.getReturnType(), "clear").get(0);
            List<Method> sizeList = ProjectIntrospection.getOrFindMethodList(project, this, getTokenList.getReturnType(), "size");
            if (sizeList.isEmpty()) {
                sizeList = ProjectIntrospection.getOrFindMethodList(project, this, getTokenList.getReturnType(), "Size");
            }
            getSize = sizeList.get(0);
        } catch (Exception e) {
            return fail("At least one of the following can't be found: setString, getTokenList, ClearableHistory.clear, ClearableHistory.getSize");
        }

        boolean[] results = checkClear(scannerBeanConstructor, setString, getTokenList, clear, getSize);
        int count = count(results, true);
        
        if (count == 0) {
            return fail("ClearableHistory does not work");
        } if (count == 1) {
            if (results[0]) {
                return partialPass(0.25, "ClearableHistory didn't clear");
            } else {
                return partialPass(0.25, "ClearableHistory didn't start with contents");
            }
        } else {
            return pass();
        }
    }
    
    private static boolean[] checkClear(Constructor<?> bridgeSceneConstructor, Method setString, Method getTokenList, Method clear, Method getSize) {
        boolean[] ret = new boolean[2];
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(setString, "0 a \"stuff\" "),                 // 0
            MethodEnvironment.get(getTokenList),                                // 1
            MethodEnvironment.get(MethodExecutionTestCase.M1_RET, getSize),     // 2
            MethodEnvironment.get(MethodExecutionTestCase.M1_RET, clear),       // 3
            MethodEnvironment.get(MethodExecutionTestCase.M1_RET, getSize),     // 4
        };
        
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneConstructor, new Object[]{}, methods);
        System.err.println(Arrays.toString(exData));
        
        ret[0] = checkNEqual(exData, 2, new Integer(0));
        ret[1] = checkEqual(exData, 4, new Integer(0));
        
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
        if (a >= results.length) {
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
