package gradingTools.comp401f15.assignment6.testcases.commands.methods;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
public class ApproachMethodFunctionTestCase extends BasicTestCase {

    public ApproachMethodFunctionTestCase() {
        super("Approach Method Functionality Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class bridgeSceneClass = IntrospectionUtil.getOrFindClass(project, this, "BridgeScene");
        List<Method> approachMList = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "approach");
        if (approachMList == null || approachMList.isEmpty()) {
            return fail("Can't find approach method in class " + bridgeSceneClass.getTypeName());
        }
        Method approach = approachMList.get(0);
        Constructor<?> bridgeSceneConstructor;
        try {
            bridgeSceneConstructor = bridgeSceneClass.getConstructor();
        } catch (NoSuchMethodException | SecurityException ex) {
            return fail("Can't access BridgeScene constructor");
        }
        if (bridgeSceneConstructor == null) {
            return fail("Can't find empty BridgeScene constructor");
        }
        
        Method getOccupied = null;
        Method getArthur = null;
        Method getLancelot = null;
        Method[] getAvatarX = new Method[2];
        
        try {
            getOccupied = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Occupied").get(0);
            getArthur = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Arthur").get(0);
            getLancelot = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Lancelot").get(0);
            
            List<Method> lm = IntrospectionUtil.getOrFindMethodList(project, this, getArthur.getReturnType(), "Head");
            lm = lm.stream().filter((s)->s.getName().contains("get")).collect(Collectors.toList());
            getAvatarX[0] = lm.get(0);
            lm = IntrospectionUtil.getOrFindMethodList(project, this, getAvatarX[0].getReturnType(), "X");
            lm = lm.stream().filter((s)->s.getName().contains("get")).collect(Collectors.toList());
            getAvatarX[1] = lm.get(0);
        } catch (Exception e) {
            return fail("Can't find at least one of the following: occupied getter, getArthur, getLancelot, Avatar.getX");
        }

        boolean[] results = checkMovement(bridgeSceneConstructor, approach, getOccupied, getArthur, getLancelot, getAvatarX);
        
        int correct = count(results, true);
        if (correct == 0) {
            return fail("Incorrect or no approach");
        } else if (correct == 3) {
            return pass();
        } else {
            int raw = results[0] ? 1 : 0;
            raw += results[1] ? 2 : 0;
            raw += results[2] ? 2 : 0;
            double score = ((double)raw) / 5;
            String message = buildMessage(results);
            return partialPass(score, message);
        }
    }
    
    private static boolean[] checkMovement(Constructor<?> bridgeSceneConstructor, Method approach, Method occupied, Method getArthur, Method getLancelot, Method[] getX) {
        boolean[] ret = new boolean[]{false, false, false};
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(occupied),                                // 0
            MethodEnvironment.get(getArthur),                               // 1
            MethodEnvironment.get(getLancelot),                             // 2
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getX),    // 3
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getX),    // 4
            MethodEnvironment.get(approach, MethodExecutionTestCase.M1_RET),// 5
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getX),    // 6
            MethodEnvironment.get(occupied),                                // 7
            MethodEnvironment.get(approach, MethodExecutionTestCase.M2_RET),// 8
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getX),    // 9
            MethodEnvironment.get(occupied),                                // 10
        };
        
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneConstructor, new Object[]{}, methods);
        if (exData[0] instanceof Boolean && exData[7] instanceof Boolean && exData[10] instanceof Boolean) {
            ret[0] = (Boolean)exData[10] && (Boolean)exData[7]&& !(Boolean)exData[0];
            if (ret[0]) {
                System.out.println("Occupied changed properly");
            } else {
                System.out.println("Occupied set improperly");
            }
        }
        if (checkGTValue(exData, 6, 3) == 0) {
            System.out.println("Arthur moved");
            ret[1] = true;
        } else {
            System.out.println("Arthur didn't move");
        }
        if (checkEqualValue(exData, 9, 4) == 0) {
            System.out.println("Lancelot stationary");
            ret[2] = true;
        } else {
            System.out.println("Lancelot moved");
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
    
    private static String buildMessage(boolean[] notes) {
        StringBuilder ret = new StringBuilder();
        if (notes[0] == false) {
            ret.append("Does not set occupied properly\n");
        }
        if (notes[1] == false) {
            ret.append("Does not move knight when not occupied\n");
        }
        if (notes[2] == false) {
            ret.append("Moves knigth when occupied");
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
