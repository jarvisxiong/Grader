package gradingTools.comp401f15.assignment7.testCases.commandInterpreter;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ExecutionUtil;
import grader.util.IntrospectionUtil;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

/**
 *
 * @author Andrew Vitkus
 */
public class SayCommandInterpretedTestCase extends BasicTestCase {

    private static final String TEST_COMMAND = "say \"What is your name?\"";
    private static final String EXPECTED_STRING = "What is your name?";
        
    public SayCommandInterpretedTestCase() {
        super("Say command test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class<?> commandInterpreterClass = IntrospectionUtil.findClass(project, null, "CommandInterpreter", ".*[cC]ommand.*[iI]nterpreter.*", ".*[cC]ommand[iI]nterpreter.*");
        Class<?> bridgeSceneClass = IntrospectionUtil.findClass(project, null, "BridgeScene", ".*[bB]ridge.*[sS]cene.*", ".*[bB]ridge[sS]cene.*");
        Class<?> scannerBeanClass = IntrospectionUtil.findClass(project, null, "ScannerBean", ".*[sS]canner.*[bB]ean.*", ".*[sS]canner[bB]ean.*");
        
        Constructor<?> commandInterpreterConstructor = null;
        Constructor<?> bridgeSceneConstructor;
        Constructor<?> scannerBeanConstructor;
        
        boolean bridgeFirst = true;
        try {
            Constructor<?>[] commandInterpreterConstructors = commandInterpreterClass.getConstructors();
            for(Constructor<?> c : commandInterpreterConstructors) {
                Object[] params = c.getParameterTypes();
                if (params.length != 2) {
                    continue;
                }
                if (((Class<?>)params[0]).isAssignableFrom(bridgeSceneClass)
                        && ((Class<?>)params[1]).isAssignableFrom(scannerBeanClass)) {
                    commandInterpreterConstructor = c;
                    bridgeFirst = true;
                } else if (((Class<?>)params[0]).isAssignableFrom(scannerBeanClass)
                        && ((Class<?>)params[1]).isAssignableFrom(bridgeSceneClass)) {
                    commandInterpreterConstructor = c;
                    bridgeFirst = false;
                }
            }
            Objects.requireNonNull(commandInterpreterConstructor);
            bridgeSceneConstructor = bridgeSceneClass.getConstructor();
            scannerBeanConstructor = scannerBeanClass.getConstructor();
        } catch(Exception e) {
            e.printStackTrace();
            return fail("Couldn't find correct constructor for CommandInterpreter, BridgeScene, or ScannerBean");
        }
        
        Method setCommand = null;
        Method getScannedString = null;
        Method approach = null;
        Method getArthur = null;
        Method getGuard = null;
        Method getStringShape = null;
        Method getText = null;
        
        try {
            setCommand = IntrospectionUtil.getOrFindMethodList(project, this, commandInterpreterClass, "Command").stream().filter((Method m) -> m.getName().contains("set")).collect(Collectors.toList()).get(0);
            getScannedString = IntrospectionUtil.getOrFindMethodList(project, this, scannerBeanClass, "ScannedString").stream().filter((Method m) -> m.getName().contains("get")).collect(Collectors.toList()).get(0);
            approach = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "approach").get(0);
            getArthur = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Arthur").get(0);
            getGuard = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Guard").stream().filter((Method m) -> !m.getName().contains("rea")).collect(Collectors.toList()).get(0);
            for(Method m : getGuard.getReturnType().getMethods()) {
                StructurePattern structurePattern = m.getReturnType().getAnnotation(StructurePattern.class);
                if (structurePattern != null && StructurePatternNames.STRING_PATTERN.equals(structurePattern.value())) {
                    getStringShape = m;
                    break;
                }
            }
            List<Method> lm = IntrospectionUtil.getOrFindMethodList(project, this, getStringShape.getReturnType(), "Text");
            lm = lm.stream().filter((s)->s.getName().contains("get")&&CharSequence.class.isAssignableFrom(s.getReturnType())).collect(Collectors.toList());
            if (lm.isEmpty()) {
                lm = IntrospectionUtil.getOrFindMethodList(project, this, getStringShape.getReturnType(), "String");
                lm = lm.stream().filter((s)->s.getName().contains("get")&&CharSequence.class.isAssignableFrom(s.getReturnType())).collect(Collectors.toList());
            }
            getText = lm.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("At least one of the following can't be found: setCommand, getScannedString, approach, getArthur, getGuard, avatar speech getter, String_Pattern getText/getString");
        }
        
        boolean[] results = checkInterpretSay(commandInterpreterConstructor, bridgeSceneConstructor, scannerBeanConstructor, bridgeFirst, setCommand, getScannedString, approach, getArthur, getGuard, getStringShape, getText);

        if (results.length == 1) {
            return fail("Failed to instantiate CommandInterpreter");
        } else {
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
    }
        
    private static boolean[] checkInterpretSay(Constructor<?> commandInterpreterConstructor, Constructor<?> bridgeSceneConstructor, Constructor<?> scannerBeanConstructor, boolean bridgeFirst, Method setCommand, Method getScannedString, Method approach, Method getArthur, Method getGuard, Method getStringShape, Method getText) {
        boolean[] ret = new boolean[3];
        Object scannerBeanInstance = ExecutionUtil.timedInvoke(scannerBeanConstructor, new Object[]{});
        Object bridgeSceneInstance = ExecutionUtil.timedInvoke(bridgeSceneConstructor, new Object[]{});
        
        
        MethodExecutionTestCase.MethodEnvironment[] methods = new MethodExecutionTestCase.MethodEnvironment[]{
            MethodEnvironment.get(bridgeSceneInstance, getArthur),                                  // 0
            MethodEnvironment.get(bridgeSceneInstance, getGuard),                                   // 1
            MethodEnvironment.get(MethodExecutionTestCase.M1_RET, getStringShape),                  // 2
            MethodEnvironment.get(bridgeSceneInstance, approach, MethodExecutionTestCase.M0_RET),   // 3
            MethodEnvironment.get(setCommand, TEST_COMMAND),                                        // 4
            MethodEnvironment.get(scannerBeanInstance, getScannedString),                           // 5
            MethodEnvironment.get(MethodExecutionTestCase.M2_RET, getText)                          // 6
        };
        
        Object[] exData;
        if (bridgeFirst) {
            exData = MethodExecutionTestCase.invoke(commandInterpreterConstructor,
                new Object[]{bridgeSceneInstance, scannerBeanInstance}, methods);
        } else {
            exData = MethodExecutionTestCase.invoke(commandInterpreterConstructor,
                new Object[]{scannerBeanInstance, bridgeSceneInstance}, methods);
        }
        System.err.println(Arrays.toString(exData));
        
        if (exData.length == 1) {
            return new boolean[]{false};
        }
        
        ret[0] = checkNotIntance(exData, 4, Exception.class);
        ret[1] = checkEqual(exData, 5, TEST_COMMAND);
        ret[2] = checkEqual(exData, 6, EXPECTED_STRING);
        
        System.err.println(Arrays.toString(ret));
        
        return ret;
    }
    
    private static boolean checkNEqual(Object[] results, int a, Object value) {
        if (a >= results.length) {
            return false;
        }
        
        return !Objects.equals(results[a], value);
    }
    
    private static boolean checkEqual(Object[] results, int a, Object value) {
        if (a >= results.length) {
            return false;
        }
        
        return Objects.equals(results[a], value);
    }
    
    private static boolean checkNotIntance(Object[] results, int a, Class<?> c) {
        return a < results.length && !c.isInstance(results[a]);
    }
    
    private static String buildMessage(boolean[] notes) {
        StringBuilder ret = new StringBuilder();
        if (notes[0] == false) {
            ret.append("Exception when setting command\n");
        }
        if (notes[1] == false) {
            ret.append("The ScannerBean's ScannedString is not set properly\n");
        }
        if (notes[2] == false) {
            ret.append("Does not call say (or error in approach making say untestable)\n");
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
