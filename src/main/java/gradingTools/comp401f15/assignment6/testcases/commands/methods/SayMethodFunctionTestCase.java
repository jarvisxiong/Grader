package gradingTools.comp401f15.assignment6.testcases.commands.methods;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCase;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

/**
 *
 * @author Andrew
 */
public class SayMethodFunctionTestCase extends BasicTestCase {

    public SayMethodFunctionTestCase() {
        super("Say Method Functionality Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class bridgeSceneClass = IntrospectionUtil.getOrFindClass(project, this, "BridgeScene");
        List<Method> sayMList = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "say", "say");
        if (sayMList == null || sayMList.isEmpty()) {
            return fail("Can't find say method in class " + bridgeSceneClass.getTypeName());
        }
        Method say = sayMList.get(0);
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
        Method pass = null;
        Method getOccupied = null;
        Method getKnightTurn = null;
        Method getArthur = null;
        Method getGuard = null;
        Method getStringShape = null;
        Method getText = null;
        
        try {
            approach = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "approach").get(0);
            pass = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "pass").get(0);
            getOccupied = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Occupied").get(0);
            getKnightTurn = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "KnightTurn").get(0);
            getArthur = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Arthur").get(0);
            getGuard = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Guard").get(0);
            for(Method m : getArthur.getReturnType().getMethods()) {
                StructurePattern structurePattern = m.getReturnType().getAnnotation(StructurePattern.class);
                if (structurePattern != null && StructurePatternNames.STRING_PATTERN.equals(structurePattern.value())) {
                    getStringShape = m;
                    break;
                }
            }
            List<Method> lm = IntrospectionUtil.getOrFindMethodList(project, this, getStringShape.getReturnType(), "Text");
            if (lm.isEmpty()) {
                lm = IntrospectionUtil.getOrFindMethodList(project, this, getStringShape.getReturnType(), "String");
            }
            getText = lm.get(0);
        } catch (Exception e) {
            return fail("At least one of the following can't be found: approach, pass, occupied getter, knight turn getter, getArthur, getLancelot, getGuard, avatar speech getter, String_Pattern getText/getString");
        }

        boolean[] results = checkPass(bridgeSceneConstructor, say, approach, pass, getOccupied, getKnightTurn, getArthur, getGuard, getStringShape, getText);
        
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
    
    private static boolean[] checkPass(Constructor<?> bridgeSceneConstructor, Method say, Method approach, Method pass, Method getOccupied, Method getKnightTurn, Method getArthur, Method getGuard, Method getStringShape, Method getText) {
        boolean[] ret = new boolean[13];
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(getArthur),                                       // 0
            MethodEnvironment.get(getGuard),                                        // 1
            MethodEnvironment.get(MethodExecutionTestCase.M0_RET, getStringShape),  // 2
            MethodEnvironment.get(MethodExecutionTestCase.M1_RET, getStringShape),  // 3
            MethodEnvironment.get(getOccupied),                                     // 4
            MethodEnvironment.get(getKnightTurn),                                   // 5
            MethodEnvironment.get(say, "What is your name?"),                       // 6    // check say after nothing
            MethodEnvironment.get(MethodExecutionTestCase.M2_RET, getText),         // 7
            MethodEnvironment.get(MethodExecutionTestCase.M3_RET, getText),         // 8
            MethodEnvironment.get(approach, MethodExecutionTestCase.M0_RET),        // 9
            MethodEnvironment.get(getOccupied),                                     // 10
            MethodEnvironment.get(getKnightTurn),                                   // 11
            MethodEnvironment.get(say, "What is your name?"),                       // 12   // check say after approach
            MethodEnvironment.get(MethodExecutionTestCase.M2_RET, getText),         // 13
            MethodEnvironment.get(MethodExecutionTestCase.M3_RET, getText),         // 14
            MethodEnvironment.get(getOccupied),                                     // 15
            MethodEnvironment.get(getKnightTurn),                                   // 16
            MethodEnvironment.get(say, "It is Arthur, King of the Brittons."),      // 17   // check say after approach, say
            MethodEnvironment.get(MethodExecutionTestCase.M2_RET, getText),         // 18
            MethodEnvironment.get(MethodExecutionTestCase.M3_RET, getText),         // 19
            MethodEnvironment.get(getKnightTurn),                                   // 20
            MethodEnvironment.get(pass),                                            // 21
            MethodEnvironment.get(getOccupied),                                     // 22
            MethodEnvironment.get(getKnightTurn),                                   // 23
            MethodEnvironment.get(say, "What is your name?"),                       // 24   // check say after approach, say, pass
            MethodEnvironment.get(MethodExecutionTestCase.M2_RET, getText),         // 25
            MethodEnvironment.get(MethodExecutionTestCase.M3_RET, getText)          // 26
        };
        
        Object[] exData = MethodExecutionTestCase.invoke(bridgeSceneConstructor, new Object[]{}, methods);
        System.err.println(Arrays.toString(exData));
        
        ret[0] = checkNotIntance(exData, 6, Exception.class);
        ret[1] = checkCorrectAction(exData, 2, 7, 4, 5, false);
        ret[2] = checkCorrectAction(exData, 7, 13, 10, 11, true);
        ret[3] = checkCorrectAction(exData, 8, 14, 10, 11, false);
        ret[4] = checkEqual(exData, 14, "What is your name?");
        ret[5] = checkIs(exData, 16, true);
        ret[6] = checkCorrectAction(exData, 13, 18, 15, 16, true);
        ret[7] = checkCorrectAction(exData, 14, 19, 15, 16, false);
        ret[8] = checkEqual(exData, 18, "It is Arthur, King of the Brittons.");
        ret[9] = checkIs(exData, 21, false);
        ret[10] = checkNotIntance(exData, 24, Exception.class);
        ret[11] = checkCorrectAction(exData, 18, 25, 22, 23, true);
        ret[12] = checkCorrectAction(exData, 19, 26, 22, 23, false);
        
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
        boolean shouldChange = bOccupied && (Boolean.compare(bKnightTurn, checkKnight) == 0);
        
        return (Boolean.compare(same, shouldChange)==0) || (!shouldChange && checkEqual(results, now, ""));
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
    
    private static String buildMessage(boolean[] notes) {
        StringBuilder ret = new StringBuilder();
        if (notes[0] == false) {
            ret.append("Error calling say before approach\n");
        }
        if (notes[1] == false) {
            ret.append("Incorrect guard text change calling say before approach\n");
        }
        if (notes[2] == false) {
            ret.append("Incorrect knight text change calling say after approach\n");
        }
        if (notes[3] == false) {
            ret.append("Incorrect guard text change calling say after approach\n");
        }
        if (notes[4] == false) {
            ret.append("Guard text set to wrong value\n");
        }
        if (notes[5] == false) {
            ret.append("KnightTurn set incorrectly after approach, say\n");
        }
        if (notes[6] == false) {
            ret.append("Incorrect knight text change calling fail after approach, say\n");
        }
        if (notes[7] == false) {
            ret.append("Incorrect guard text change calling fail after approach, say\n");
        }
        if (notes[8] == false) {
            ret.append("Knight text set to wrong value\n");
        }
        if (notes[9] == false) {
            ret.append("KnightTurn set incorrectly after approach, say, say\n");
        }
        if (notes[10] == false) {
            ret.append("Error calling say affter approach, say, say, pass\n");
        }
        if (notes[11] == false) {
            ret.append("Incorrect knight text change calling fail after approach, say, say, pass\n");
        }
        if (notes[12] == false) {
            ret.append("Incorrect guard text change calling fail after approach, say, say, pass\n");
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
