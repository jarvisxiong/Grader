package gradingTools.comp401f15.assignment10.testCases.preConditions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.util.ProjectIntrospection;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;

/**
 *
 * @author Andrew
 */
public class FailedMethodPreconditionTestCase extends BasicTestCase {

    public FailedMethodPreconditionTestCase() {
        super("Fail Method Precondition Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class bridgeSceneClass = ProjectIntrospection.getOrFindClass(project, this, "BridgeScene");
        List<Method> failMList = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "fail", "failed");
        if (failMList == null || failMList.isEmpty()) {
            return fail("Can't find failed method in class " + bridgeSceneClass.getTypeName());
        }
        Method fail = failMList.get(0);
        Constructor<?> bridgeSceneConstructor;
        try {
            bridgeSceneConstructor = bridgeSceneClass.getConstructor();
        } catch (NoSuchMethodException | SecurityException ex) {
            return fail("Can't access BridgeScene constructor");
        }
        if (bridgeSceneConstructor == null) {
            return fail("Can't find empty BridgeScene constructor");
        }
        
        Method approach = null;
        Method say = null;
        Method getOccupied = null;
        Method getKnightTurn = null;
        Method getArthur = null;
        Method getLancelot = null;
        Method getGuard = null;
        Method[] getAvatarY = new Method[2];
        //Method getHead = null;
        //Method getAvatarX = null;
        //Method getAvatarY = null;
 
        try {
            approach = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "approach").get(0);
            say = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "say").get(0);
            getOccupied = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "Occupied").get(0);
            getKnightTurn = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "KnightTurn").get(0);
            getArthur = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "Arthur").get(0);
            getLancelot = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "Lancelot").get(0);
            getGuard = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "Guard").stream().filter((Method m) -> !m.getName().contains("rea")).collect(Collectors.toList()).get(0);

            List<Method> lm = ProjectIntrospection.getOrFindMethodList(project, this, getArthur.getReturnType(), "Head");
            lm = lm.stream().filter((s)->s.getName().contains("get")).collect(Collectors.toList());
            getAvatarY[0] = lm.get(0);
            lm = ProjectIntrospection.getOrFindMethodList(project, this, getAvatarY[0].getReturnType(), "Y");
            lm = lm.stream().filter((s)->s.getName().contains("get")).collect(Collectors.toList());
            getAvatarY[1] = lm.get(0);
        } catch (Exception e) {
            return fail("At least one of the following can't be found: approach, say, occupied getter, knight turn getter, getArthur, getLancelot, getGuard, Avatar.getY");
        }

        boolean[] results = checkPass(bridgeSceneConstructor, fail, approach, say, getOccupied, getKnightTurn, getArthur, getLancelot, getGuard, getAvatarY);
        
        int correct = count(results, true);
        int possible = results.length;
        if (correct == 0) {
            return fail("Incorrect or no fail");
        } else if (correct == possible) {
            return pass();
        } else {
            double score = ((double)correct) / possible;
            String message = buildMessage(results);
            return partialPass(score, message);
        }
    }
    
    private static boolean[] checkPass(Constructor<?> bridgeSceneConstructor, Method fail, Method approach, Method say, Method getOccupied, Method getKnightTurn, Method getArthur, Method getLancelot, Method getGuard, Method[] getY) {
        boolean[] ret = new boolean[13];
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(getArthur),                                   // 0
            MethodEnvironment.get(getLancelot),                                 // 1
            MethodEnvironment.get(getGuard),                                    // 2
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getY),        // 3
            MethodEnvironment.get(getOccupied),                                 // 4
            MethodEnvironment.get(getKnightTurn),                               // 5
            MethodEnvironment.get(fail),                                        // 6    // check fail after nothing
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getY),        // 7
            MethodEnvironment.get(approach, MethodExecutionTestCase.M1_RET),    // 8
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getY),        // 9
            MethodEnvironment.get(getOccupied),                                 // 10
            MethodEnvironment.get(getKnightTurn),                               // 11
            MethodEnvironment.get(fail),                                        // 12   // check fail after approach (knight fail)
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getY),        // 13
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getY),        // 14
            MethodEnvironment.get(getOccupied),                                 // 15   // should be false
            MethodEnvironment.get(getKnightTurn),                               // 16
            MethodEnvironment.get(fail),                                        // 17   // check fail after (hopefully successful) knight fail
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getY),        // 18
            MethodEnvironment.get(approach, MethodExecutionTestCase.M0_RET),    // 19
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getY),        // 20
            MethodEnvironment.get(say, "What is your name?"),                   // 21
            MethodEnvironment.get(getOccupied),                                 // 22
            MethodEnvironment.get(getKnightTurn),                               // 23
            MethodEnvironment.get(fail),                                        // 24   // check fail after approach, say (guard fail)
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getY),        // 25
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getY),        // 26
            MethodEnvironment.get(getOccupied),                                 // 27
            MethodEnvironment.get(getKnightTurn),                               // 28
            MethodEnvironment.get(fail),                                        // 29   // check fail after (hopefully successful) guard fail
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getY),        // 30
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M2_RET, getY),        // 31
            MethodEnvironment.get(getOccupied)                                  // 32   // should be true
        };
        
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneConstructor, new Object[]{}, methods);
        System.err.println(Arrays.toString(exData));
        ret[0] = 6 < exData.length && !(exData[6] instanceof Exception);
        ret[1] = checkEqualValue(exData, 3, 7) == 0;
        ret[2] = correctAction(exData, 9, 13, 10, 11, true, false) == 0;
        ret[3] = correctAction(exData, 7, 14, 10, 11, false, false) == 0;
        ret[4] = 15 < exData.length && (exData[15] instanceof Boolean) && ((Boolean)exData[15]) == false;
        ret[5] = 17 < exData.length && !(exData[17] instanceof Exception);
        ret[6] = correctAction(exData, 14, 18, 15, 16, false, false) == 0;
        ret[7] = correctAction(exData, 20, 25, 22, 23, true, false) == 0;
        ret[8] = correctAction(exData, 18, 26, 22, 23, false, false) == 0;
        ret[9] = 29 < exData.length && !(exData[29] instanceof Exception);
        ret[10] = correctAction(exData, 25, 30, 27, 28, true, true) == 0;
        ret[11] = correctAction(exData, 26, 31, 27, 28, false, true) == 0;
        ret[12] = 15 < exData.length && (exData[32] instanceof Boolean) && ((Boolean)exData[32]) == true;
        
        System.out.println(Arrays.toString(ret));
        return ret;
    }
    
    private static int correctAction(Object[] results, int start, int end, int occupied, int knightTurn, boolean checkKnight, boolean guardHasFailed) {
        int movedValue = checkEqualValue(results, start, end);
        if (movedValue != 0 && movedValue != 4) {
            return movedValue;
        } 
        boolean moved = movedValue == 4;
        if (results.length < occupied || results.length < knightTurn) {
            return 1; // checking out of bounds
        }
        Object oOccupied = results[occupied];
        Object oKnightTurn = results[knightTurn];
        if (oOccupied instanceof Exception || oKnightTurn instanceof Exception) {
            return 2; // error in execution
        }
        if (!(oOccupied instanceof Boolean) || !(oKnightTurn instanceof Boolean)) {
            return 3; // not boolean
        }
        boolean bOccupied = (Boolean)oOccupied;
        boolean bKnightTurn = (Boolean)oKnightTurn;
        
        if (Boolean.compare(moved, bOccupied && !guardHasFailed && (checkKnight ^ bKnightTurn)) == 0) {
            return 0; // correct
        } else {
            return 4; // incorrect
        }
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
            ret.append("Error calling fail before approach\n");
        }
        if (notes[1] == false) {
            ret.append("Incorrect guard action calling fail before approach\n");
        }
        if (notes[2] == false) {
            ret.append("Incorrect knight action calling fail after approach\n");
        }
        if (notes[3] == false) {
            ret.append("Incorrect guard action calling fail after approach\n");
        }
        if (notes[4] == false) {
            ret.append("Occupied set incorrectly after approach, fail\n");
        }
        if (notes[5] == false) {
            ret.append("Error calling fail after approach, fail\n");
        }
        if (notes[6] == false) {
            ret.append("Incorrect guard action calling fail after approach, fail\n");
        }
        if (notes[7] == false) {
            ret.append("Incorrect knight action calling fail after approach, fail, fail, approach, say\n");
        }
        if (notes[8] == false) {
            ret.append("Incorrect guard action calling fail after approach, fail, fail, approach, say\n");
        }
        if (notes[9] == false) {
            ret.append("Error calling fail after approach, fail, fail, approach, say, fail\n");
        }
        if (notes[10] == false) {
            ret.append("Incorrect knight action calling fail after approach, fail, fail, approach, say, fail\n");
        }
        if (notes[11] == false) {
            ret.append("Incorrect guard action calling fail after approach, fail, fail, approach, say, fail\n");
        }
        if (notes[12] == false) {
            ret.append("Occupied set incorrectly after approach, fail, fail, approach, say, fail, fail");
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
