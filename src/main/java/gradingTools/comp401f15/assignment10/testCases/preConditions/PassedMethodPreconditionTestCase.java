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
public class PassedMethodPreconditionTestCase extends BasicTestCase {

    public PassedMethodPreconditionTestCase() {
        super("Pass Method Precondition Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class bridgeSceneClass = ProjectIntrospection.getOrFindClass(project, this, "BridgeScene");
        List<Method> passMList = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "pass", "passed");
        if (passMList == null || passMList.isEmpty()) {
            return fail("Can't find passed method in class " + bridgeSceneClass.getTypeName());
        }
        Method pass = passMList.get(0);
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
        Method getGorge = null;
        Method[] getGorgeX = null;
        Method getArthur = null;
        Method[] getAvatarX = new Method[2];
        
        try {
            approach = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "approach").get(0);
            say = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "say").get(0);
            getOccupied = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "Occupied").get(0);
            getKnightTurn = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "KnightTurn").get(0);
            getGorge = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "Gorge").get(0);
            getGorgeX = MethodExecutionTestCase.recursiveFindMethod(getGorge.getReturnType(), "getX", "X");
            getArthur = ProjectIntrospection.getOrFindMethodList(project, this, bridgeSceneClass, "Arthur").get(0);
            
            List<Method> lm = ProjectIntrospection.getOrFindMethodList(project, this, getArthur.getReturnType(), "Head");
            lm = lm.stream().filter((s)->s.getName().contains("get")).collect(Collectors.toList());
            getAvatarX[0] = lm.get(0);
            lm = ProjectIntrospection.getOrFindMethodList(project, this, getAvatarX[0].getReturnType(), "X");
            lm = lm.stream().filter((s)->s.getName().contains("get")).collect(Collectors.toList());
            getAvatarX[1] = lm.get(0);    
        } catch (Exception e) {
            return fail("At least one of the following can't be found: approach, say, occupied getter, knight turn getter, getGorge, Gorge.getX, getArthur, Avatar.getX");
        }

        boolean[] results = checkPass(bridgeSceneConstructor, pass, approach, say, getOccupied, getKnightTurn, getGorge, getGorgeX, getArthur, getAvatarX);
        
        int correct = count(results, true);
        int possible = results.length;
        if (correct == 0) {
            return fail("Incorrect or no pass");
        } else if (correct == possible) {
            return pass();
        } else {
            double score = ((double)correct) / possible;
            String message = buildMessage(results);
            return partialPass(score, message);
        }
    }
    
    private static boolean[] checkPass(Constructor<?> bridgeSceneConstructor, Method pass, Method approach, Method say, Method getOccupied, Method getKnightTurn, Method getGorge, Method[] getGorgeX, Method getArthur, Method[] getX) {
        boolean[] ret = new boolean[7];
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(getGorge),                                    // 0
            MethodEnvironment.get(getArthur),                                   // 1
            MethodEnvironment.get(pass),                                        // 2
            MethodEnvironment.get(approach, MethodExecutionTestCase.M1_RET),    // 3
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getX),        // 4
            MethodEnvironment.get(say, "What is your name?"),                   // 5
            MethodEnvironment.get(getOccupied),                                 // 6
            MethodEnvironment.get(getKnightTurn),                               // 7
            MethodEnvironment.get(pass),                                        // 8
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getX),        // 9
            MethodEnvironment.get(say, "It is Arthur, King of the Britons"),    // 10
            MethodEnvironment.get(getOccupied),                                 // 11
            MethodEnvironment.get(getKnightTurn),                               // 12
            MethodEnvironment.get(pass),                                        // 13
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getX),        // 14
            MethodEnvironment.get(getOccupied),                                 // 15
            MethodEnvironment.get(getKnightTurn),                               // 16
            MethodEnvironment.get(pass),                                        // 17
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M1_RET, getX),        // 18
            MethodEnvironment.get(getOccupied),                                 // 19
            MethodEnvironment.get(MethodExecutionTestCase.CYCLIC_GET_PROPERTY, MethodExecutionTestCase.M0_RET, getGorgeX)    // 20
        };
        
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneConstructor, new Object[]{}, methods);
        System.err.println(Arrays.toString(exData));
        ret[0] = 2 < exData.length && !(exData[2] instanceof Exception);
        ret[1] = correctAction(exData, 4, 9, 6, 7) == 0;
        ret[2] = correctAction(exData, 9, 14, 11, 12) == 0;
        ret[3] = 17 < exData.length && !(exData[17] instanceof Exception);
        ret[4] = correctAction(exData, 14, 18, 15, 16) == 0;
        ret[5] = 15 < exData.length && (exData[15] instanceof Boolean) && ((Boolean)exData[15]) == false;
        ret[5] &= (exData[19] instanceof Boolean) && ((Boolean)exData[19]) == false;
        ret[6] = checkGTValue(exData, 18, 20) == 0;
        
        System.out.println(Arrays.toString(ret));
        return ret;
    }
    
    private static int correctAction(Object[] results, int start, int end, int occupied, int knightTurn) {
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
        
        if (Boolean.compare(moved, bOccupied && !bKnightTurn) == 0) {
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
            ret.append("Error calling pass before approach\n");
        }
        if (notes[1] == false) {
            ret.append("Incorrect action calling pass after approach, say\n");
        }
        if (notes[2] == false) {
            ret.append("Incorrect action calling pass after approach, say, say\n");
        }
        if (notes[3] == false) {
            ret.append("Error calling pass after approach, say, say, pass\n");
        }
        if (notes[4] == false) {
            ret.append("Incorrect action calling pass after approach, say, say, pass\n");
        }
        if (notes[5] == false) {
            ret.append("Does not set occupied false on success (or approach/say does not function so assuptions failed\n");
        }
        if (notes[6] == false) {
            ret.append("Avatar does not move past gorge on pass");
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
