package gradingTools.comp401f15.assignment6.testcases.commands.methods;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;
import grader.util.ProjectIntrospection;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;

/**
 *
 * @author Andrew
 */
public class ScrollMethodFunctionTestCase extends BasicTestCase {

    public ScrollMethodFunctionTestCase() {
        super("Scroll Method Functionality Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class bridgeSceneClass = ProjectIntrospection.getOrFindClass(project, this, "BridgeScene");
        List<Method> scrollMList = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "scroll");
        if (scrollMList == null || scrollMList.isEmpty()) {
            return fail("Can't find scroll method in class " + bridgeSceneClass.getTypeName());
        }
        Method scroll = scrollMList.get(0);
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
        Method[] getKnightY = null;
        Method getGuardArea = null;
        Method[] getGuardX = null;
        Method[] getGuardY = null;
        
        boolean useKnightArea = true;
        boolean useGuardArea = true;
        
        try {
            getKnightArea = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "KnightArea").get(0);
            getKnightX = MethodExecutionTestCase.recursiveFindMethod(getKnightArea.getReturnType(), "getX", "X");
            getKnightY = MethodExecutionTestCase.recursiveFindMethod(getKnightArea.getReturnType(), "getY", "Y");
        } catch (Exception e) {
            useKnightArea = false;
        }
        try {
            getGuardArea = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "GuardArea").get(0);
            getGuardX = MethodExecutionTestCase.recursiveFindMethod(getGuardArea.getReturnType(), "getX", "X");
            getGuardY = MethodExecutionTestCase.recursiveFindMethod(getGuardArea.getReturnType(), "getY", "Y");
        } catch (Exception e) {
            useGuardArea = false;
        }
        int[] results;
        if (useKnightArea) {
            if (useGuardArea) {
                System.out.println("Using both knight and guard areas");
                results = check2Areas(bridgeSceneConstructor, scroll, getKnightArea, getKnightX, getKnightY, getGuardArea, getGuardX, getGuardY);
            } else {
                System.out.println("Using knight area");
                results = check1Area(bridgeSceneConstructor, scroll, getKnightArea, getKnightX, getKnightY);
            }
        } else if (useGuardArea) {
                System.out.println("Using guard area");
            results = check1Area(bridgeSceneConstructor, scroll, getGuardArea, getGuardX, getGuardY);
        } else {
            return fail("Can't access getters for either guard or knight area or their x/Y properties to check movement");
        }
        
        int correct = Arrays.stream(results).sum();
        if (correct == 0) {
            return fail("Incorrect or no scrolling");
        } else if (correct == 4) {
            return pass();
        } else {
            String[] directions = new String[]{"up", "down", "left", "right"};
            double score = ((double)correct) / 4;
            String message = "";
            for(int i = 0; i < results.length; i ++) {
                if (!message.isEmpty()) {
                    message += "\n";
                }
                message += "Does not scroll " + directions[i] + " properly";
            }
            return partialPass(score, message);
        }
    }
    
    private static int[] check1Area(Constructor<?> bridgeSceneConstructor, Method scroll, Method getArea, Method[] getX, Method[] getY) {
        int[] ret = new int[]{1, 1, 1, 1};
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(getArea),                                 // 0
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getX),    // 1
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getY),    // 2
            MethodEnvironment.get(scroll, 1, 0),                            // 3
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getX),    // 4
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getY),    // 5
            MethodEnvironment.get(scroll, -1, 0),                           // 6
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getX),    // 7
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getY),    // 8
            MethodEnvironment.get(scroll, 0, 1),                            // 9
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getX),    // 10
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getY),    // 11
            MethodEnvironment.get(scroll, 0, -1),                           // 12
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getX),    // 13
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getY)     // 14
        };
        
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneConstructor, new Object[]{}, methods);
        if (checkGTValue(exData, 4, 1) == 0 && checkEqualValue(exData, 5, 2) == 0) {
            System.out.println("1: moved ->");
        } else {
            ret[0] = 0;
            System.out.println("1: moved wrong");
        }
        if (checkLTValue(exData, 7, 4) == 0 && checkEqualValue(exData, 8, 5) == 0) {
            System.out.println("2: moved <-");
        } else {
            ret[1] = 0;
            System.out.println("2: moved wrong");
        }
        if (checkEqualValue(exData, 10, 7) == 0 && checkGTValue(exData, 11, 8) == 0) {
            System.out.println("3: moved ^");
        } else {
            ret[2] = 0;
            System.out.println("3: moved wrong");
        }
        if (checkEqualValue(exData, 13, 10) == 0 && checkLTValue(exData, 14, 11) == 0) {
            System.out.println("4: moved v");
        } else {
            ret[3] = 0;
            System.out.println("4: moved wrong");
        }
        
        System.out.println(Arrays.toString(ret));
        return ret;
    }
    
    private static int[] check2Areas(Constructor<?> bridgeSceneConstructor, Method scroll, Method getArea1, Method[] getX1, Method[] getY1,  Method getArea2, Method[] getX2, Method[] getY2) {
        int[] ret = check1Area(bridgeSceneConstructor, scroll, getArea1, getX1, getY1);
        int[] ret2 = check1Area(bridgeSceneConstructor, scroll, getArea2, getX2, getY2);
        
        for(int i = 0; i < ret.length; i ++) {
            ret[i] &= ret2[i];
        }
        
        System.out.println(Arrays.toString(ret));
        
        return ret;
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
    
    private static String buildMessage(int[] notes) {
        StringBuilder ret = new StringBuilder();
        if (notes[0] == 0) {
            ret.append("Did not scroll right properly");
        }
        if (notes[1] == 0) {
            ret.append("Did not scroll left properly");
        }
        if (notes[2] == 0) {
            ret.append("Did not scroll up properly");
        }
        if (notes[3] == 0) {
            ret.append("Did not scroll down properly");
        }
        return ret.toString();
    }
}
