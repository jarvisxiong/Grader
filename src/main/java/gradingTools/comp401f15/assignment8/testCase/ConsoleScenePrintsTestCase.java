package gradingTools.comp401f15.assignment8.testCase;

import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.*;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ExecutionUtil;
import static grader.util.ExecutionUtil.restoreOutputAndGetRedirectedOutput;
import grader.util.IntrospectionUtil;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.ExecutionData;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

/**
 *
 * @author Andrew Vitkus
 */
public class ConsoleScenePrintsTestCase extends BasicTestCase {
        
    public ConsoleScenePrintsTestCase() {
        super("Console scene view prints notifications test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        ExecutionUtil.redirectOutput();
        try {
            Class<?> consoleSceneViewClass = IntrospectionUtil.findClass(project, null, "ConsoleSceneView", ".*[cC]console.*[sS]cene.*", ".*[cC]onsole[sS]cene[vV]iew.*");
            Class<?> bridgeSceneClass = IntrospectionUtil.findClass(project, null, "BridgeScene", ".*[bB]ridge.*[sS]cene.*", ".*[bB]ridge[sS]cene.*");

            Constructor<?> consoleSceneViewConstructor = null;
            Constructor<?> bridgeSceneConstructor;

            try {
                for(Class<?> bscInterface : bridgeSceneClass.getInterfaces()) {
                    for(Constructor<?> constructor : consoleSceneViewClass.getConstructors()) {
                        if (constructor.getParameterCount() == 1) {
                            if (bscInterface.isAssignableFrom(constructor.getParameterTypes()[0])) {
                                consoleSceneViewConstructor = constructor;
                            }
                        }
                    }
                }
                Objects.requireNonNull(consoleSceneViewConstructor);
                bridgeSceneConstructor = bridgeSceneClass.getConstructor();
            } catch(Exception e) {
                e.printStackTrace(System.out);
                return fail("Couldn't find correct constructor for ConsoleSceneView or BridgeScene");
            }

            Method getArthur = null;
            Method getGuard = null;
            Method[] getX = new Method[2];
            Method[] getY = new Method[2];
            Method approach;

            try {
                approach = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "approach").get(0);
                getArthur = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Arthur").get(0);
                getGuard = IntrospectionUtil.getOrFindMethodList(project, this, bridgeSceneClass, "Guard").stream().filter((Method m) -> !m.getName().matches(".*[aA]r[ae].*")).collect(Collectors.toList()).get(0);
                for(Method m : getArthur.getReturnType().getMethods()) {
                    boolean doPick = false;
                    Class<?> retType = m.getReturnType();
                    StructurePattern structurePattern = m.getReturnType().getAnnotation(StructurePattern.class);
                    if (m.getName().matches(".*[hH]ead.*")) {
                        doPick = true;
                    } else if (structurePattern == null) {
                        if (retType.isInterface()) {
                            for(Class<?> clazz : IntrospectionUtil.getClassesForInterface(project, retType)) {
                                structurePattern = clazz.getAnnotation(StructurePattern.class);
                                if (structurePattern != null 
                                        && (StructurePatternNames.IMAGE_PATTERN.equals(structurePattern.value())
                                            || StructurePatternNames.LABEL_PATTERN.equals(structurePattern.value()))) {
                                    doPick = true;
                                    break;
                                }
                            }
                        }
                    } else if (StructurePatternNames.IMAGE_PATTERN.equals(structurePattern.value()) 
                               || StructurePatternNames.LABEL_PATTERN.equals(structurePattern.value())) {
                        doPick = true;
                    }
                    if (doPick){
                        getX[0] = m;
                        getY[0] = m;
                        break;
                    }
                }
                List<Method> lm = IntrospectionUtil.getOrFindMethodList(project, this, getX[0].getReturnType(), "X");
                lm = lm.stream().filter((s)->s.getName().contains("get")).collect(Collectors.toList());
                getX[1] = lm.get(0);

                lm = IntrospectionUtil.getOrFindMethodList(project, this, getY[0].getReturnType(), "Y");
                lm = lm.stream().filter((s)->s.getName().contains("get")).collect(Collectors.toList());
                getY[1] = lm.get(0);
            } catch (Exception e) {
                e.printStackTrace(System.out);
                return fail("At least one of the following can't be found: getScannedString, getArthur, Avatar.getHead, Head.getX, Head.getY");
            }

            boolean[] results = checkConsoleScene(consoleSceneViewConstructor, bridgeSceneConstructor, approach, getArthur, getGuard, getX, getY);

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
        } finally {
            String anOutput = restoreOutputAndGetRedirectedOutput();
            if (anOutput != null && !anOutput.isEmpty()) {
             	System.out.println(anOutput);
             	RunningProject.appendToTranscriptFile(project, getCheckable().getName(), anOutput);
            }
        }
    }
        
    private static boolean[] checkConsoleScene(Constructor<?> consoleSceneViewConstructor, Constructor<?> bridgeSceneConstructor, Method approach, Method getArthur, Method getGuard, Method[] getX, Method[] getY) {
        boolean[] ret = new boolean[4];
        Object bridgeSceneInstance = ExecutionUtil.timedInvoke(bridgeSceneConstructor, new Object[]{});
        
        Method printOutM = null;
        Method printErrM = null;
        try {
            printOutM = ConsoleScenePrintsTestCase.class.getMethod("printOut", String.class);
            printErrM = ConsoleScenePrintsTestCase.class.getMethod("printErr", String.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(ConsoleScenePrintsTestCase.class.getName()).log(Level.SEVERE, null, ex);
        }
        MethodExecutionTestCase.MethodEnvironment[] methods = new MethodExecutionTestCase.MethodEnvironment[]{
            MethodEnvironment.get(bridgeSceneInstance, getArthur),
            MethodEnvironment.get(bridgeSceneInstance, approach, MethodExecutionTestCase.M0_RET),
            MethodEnvironment.get(printOutM, "Out"),    // 6
            MethodEnvironment.get(printErrM, "Err")
        };
        
        ExecutionData[] exData = MethodExecutionTestCase.invokeGetEnvironment(consoleSceneViewConstructor, new Object[]{bridgeSceneInstance}, methods);
        for(ExecutionData data : exData) {
            System.out.println("Out: " + data.getStdOut());
            System.out.println("Err: " + data.getStdErr());
        }
        System.out.println(Arrays.toString(exData));
        
        if (exData.length == 1) {
            return new boolean[]{false};
        }
        
        ret[0] = checkNotIntance(exData, 3, Exception.class);
        
        System.out.println(Arrays.toString(ret));
        
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
            ret.append("Exception when setting command\n");
        }
        if (notes[1] == false) {
            ret.append("The ScannerBean's ScannedString is not set properly\n");
        }
        if (notes[2] == false) {
            ret.append("Does not move avatar right with positive X value\n");
        }
        if (notes[3] == false) {
            ret.append("Does not move avatar down with positive Y value\n");
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
    public static void printOut(String line) {
        System.out.println(line);
    }
    public static void printErr(String line) {
        System.err.println(line);
    }
}
