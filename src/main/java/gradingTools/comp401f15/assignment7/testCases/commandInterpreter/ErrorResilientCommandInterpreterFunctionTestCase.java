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
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

/**
 *
 * @author Andrew Vitkus
 */
public class ErrorResilientCommandInterpreterFunctionTestCase extends BasicTestCase {

    private static final String TEST_COMMAND_1 = "move Arthur 10 10";
    private static final String TEST_COMMAND_2 = "move hello world";
    private static final String TEST_COMMAND_3 = "say 1 2";
        
    public ErrorResilientCommandInterpreterFunctionTestCase() {
        super("Error Resilient Command Interpreter Function Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class<?> commandInterpreterClass = IntrospectionUtil.findClass(project, null, "CommandInterpreter", ".*[cC]ommand.*[iI]nterpreter.*", ".*[cC]ommand[iI]nterpreter.*");
        Class<?> bridgeSceneClass = IntrospectionUtil.findClass(project, null, "BridgeScene", ".*[bB]ridge.*[sS]cene.*", ".*[bB]ridge[sS]cene.*");
        Class<?> scannerBeanClass = IntrospectionUtil.findClass(project, null, "ScannerBean", ".*[sS]canner.*[bB]ean.*", ".*[sS]canner[bB]ean.*");
        
        Tags tags = commandInterpreterClass.getAnnotation(Tags.class);
        if (tags != null) {
            boolean isTagged = false;
            for(String tag : tags.value()) {
                if (tag.equals("ErrorResilient")) {
                    isTagged = true;
                    break;
                }
            }
            if (!isTagged) {
                return fail("Not tagged 'ErrorResilient' so not checking");
            }
        }
        
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
                    break;
                } else if (((Class<?>)params[0]).isAssignableFrom(scannerBeanClass)
                        && ((Class<?>)params[1]).isAssignableFrom(bridgeSceneClass)) {
                    commandInterpreterConstructor = c;
                    bridgeFirst = false;
                    break;
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
        Method getError = null;
        
        try {
            setCommand = IntrospectionUtil.getOrFindMethodList(project, this, commandInterpreterClass, "Command").stream().filter((Method m) -> m.getName().contains("set")).collect(Collectors.toList()).get(0);
            getError = (Method)getCheckable().getRequirements().getUserObject(ErrorResilientCommandInterpreterDefinedTestCase.COMMAND_INTERPETER_ERROR_METHOD);
            if (getError == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return fail("Either the command setter or error getter cannot be found");
        }
        
        boolean[] results = checkInterpretSay(commandInterpreterConstructor, bridgeSceneConstructor, scannerBeanConstructor, bridgeFirst, setCommand, getError);

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
        
    private static boolean[] checkInterpretSay(Constructor<?> commandInterpreterConstructor, Constructor<?> bridgeSceneConstructor, Constructor<?> scannerBeanConstructor, boolean bridgeFirst, Method setCommand, Method getError) {
        boolean[] ret = new boolean[10];
        Object scannerBeanInstance = ExecutionUtil.timedInvoke(scannerBeanConstructor, new Object[]{});
        Object bridgeSceneInstance = ExecutionUtil.timedInvoke(bridgeSceneConstructor, new Object[]{});
        
        
        MethodExecutionTestCase.MethodEnvironment[] methods = new MethodExecutionTestCase.MethodEnvironment[]{
            
            MethodEnvironment.get(getError),                    // 0
            MethodEnvironment.get(setCommand, TEST_COMMAND_1),  // 1
            MethodEnvironment.get(getError),                    // 2
            MethodEnvironment.get(setCommand, TEST_COMMAND_2),  // 3
            MethodEnvironment.get(getError),                    // 4
            MethodEnvironment.get(setCommand, TEST_COMMAND_3),  // 5
            MethodEnvironment.get(getError),                    // 6
            MethodEnvironment.get(setCommand, TEST_COMMAND_1),  // 7
            MethodEnvironment.get(getError)                     // 8
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
        
        String a = toStringOrElse(exData, 0, null);//, ()->exData[0] instanceof CharSequence);
        String b = toStringOrElse(exData, 2, null);//, ()->exData[2] instanceof CharSequence);
        String c = toStringOrElse(exData, 4, null);//, ()->exData[4] instanceof CharSequence);
        String d = toStringOrElse(exData, 6, null);//, ()->exData[6] instanceof CharSequence);
        String e = toStringOrElse(exData, 8, null);//, ()->exData[8] instanceof CharSequence);
        
        ret[0] = checkNotIntance(exData, 0, Exception.class);
        ret[1] = checkEqual(exData, 0, null) || (a != null && a.isEmpty());
        ret[2] = checkNotIntance(exData, 1, Exception.class);
        ret[3] = checkEqual(exData, 2, null) || (b != null && b.isEmpty());
        ret[4] = checkNotIntance(exData, 3, Exception.class);
        ret[5] = c != null && c.length() > 0;
        ret[6] = checkNotIntance(exData, 5, Exception.class);
        ret[7] = d != null && d.length() > 0;
        ret[8] = (c == null || d == null) ? false : (d.startsWith(c) ? c.equals(d) : true);
        ret[9] = checkEqual(exData, 8, null) || (e != null && e.isEmpty());
        
        System.err.println(Arrays.toString(ret));
        
        return ret;
    }
    
    private static Object getOrElse(Object[] results, int a, Object fallback) {
        if (a >= results.length) {
            return fallback;
        }
        return results[a];
    }
    
    private static Object getOrElse(Object[] results, int a, Object fallback, boolean nullValid) {
        return getOrElse(results, a, fallback, true, ()->true);
    }
    
    private static Object getOrElse(Object[] results, int a, Object fallback, BooleanSupplier isValid) {
        return getOrElse(results, a, fallback, true, isValid);
    }
    
    private static Object getOrElse(Object[] results, int a, Object fallback, boolean nullValid, BooleanSupplier isValid) {
        if (a >= results.length || !isValid.getAsBoolean()) {
            return fallback;
        }
        Object o = results[a];
        if (!nullValid && o == null) {
            return fallback;
        }
        return results[a];
    }
    
    private static String toStringOrElse(Object[] results, int a, String fallback) {
        Object o = getOrElse(results, a, fallback, false);
        if (o == null) {
            return null;
        } else {
            return o.toString();
        }
    }
    
    private static String toStringOrElse(Object[] results, int a, String fallback, BooleanSupplier isValid) {
        Object o = getOrElse(results, a, fallback, false, isValid);
        if (o == null) {
            return null;
        } else {
            return o.toString();
        }
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
            ret.append("Exception getting error before setting command\n");
        }
        if (notes[1] == false) {
            ret.append("Non-empty error message before setting command\n");
        }
        if (notes[2] == false) {
            ret.append("Exception getting error after setting valid command\n");
        }
        if (notes[3] == false) {
            ret.append("Non-empty error message after valid command\n");
        }
        if (notes[4] == false) {
            ret.append("Exception getting error after setting invalid command\n");
        }
        if (notes[5] == false) {
            ret.append("Empty error message after invalid command\n");
        }
        if (notes[6] == false) {
            ret.append("Exception getting error after setting 2 invalid commands\n");
        }
        if (notes[7] == false) {
            ret.append("Empty error message after invalid command\n");
        }
        if (notes[8] == false) {
            ret.append("Previous error messages appear to be retained with new invalid commands\n");
        }
        if (notes[9] == false) {
            ret.append("Errors not cleared when setting valid command\n");
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
