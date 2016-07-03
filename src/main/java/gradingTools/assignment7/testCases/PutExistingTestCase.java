package gradingTools.assignment7.testCases;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.Project;
import gradingTools.sharedTestCase.MethodExecutionTestCase;
import gradingTools.sharedTestCase.MethodExecutionTestCase.MethodEnvironment;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/14/13
 * Time: 12:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class PutExistingTestCase extends BasicTestCase {

    public PutExistingTestCase() {
        super("Put existing key test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class<?> tableClass = BasicProjectIntrospection.findClass(project, null, "Table", ".*[tT]able.*", ".*[tT]able.*");
        Constructor<?> tableConstructor;
        try {
            tableConstructor = tableClass.getConstructor();
        } catch (Exception e) {
            return fail("No empty constructor for Table");
        }

        Method putMethod;
        Method getMethod;
        try {
            putMethod = tableClass.getMethod("put", String.class, Object.class);
            getMethod = tableClass.getMethod("get", String.class);
        } catch (Exception e) {
            return fail("Can't find either the put or get method");
        }

        boolean[] results = checkTable(tableConstructor, putMethod, getMethod);

        if (results[0] == false) {
            return fail("Stored value did not change when putting with existing key");
        } else {
            if (results[1] == false) {
                return fail("Stored value did not change properly when putting with existing key");
            } else {
                return pass();
            }
        }
    }
    
    private static boolean[] checkTable(Constructor<?> tableConstructor, Method put, Method get) {
        boolean[] ret = new boolean[2];
        Object test = new Object();
        MethodEnvironment[] methods = new MethodEnvironment[]{
            MethodEnvironment.get(put, "hello", "world"),   // 0
            MethodEnvironment.get(get, "hello"),            // 1
            MethodEnvironment.get(put, "hello", test),    // 2
            MethodEnvironment.get(get, "hello")             // 3
        };
        Object[] exData = MethodExecutionTestCase.invoke(tableConstructor, new Object[]{}, methods);
        System.err.println(Arrays.toString(exData));
        
        ret[0] = checkNEqual(exData, 1, 3);
        ret[1] = checkEqual(exData, 3, test);
        
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
    
    private static boolean checkNEqual(Object[] results, int a, int b) {
        if (a >= results.length || b > results.length) {
            return false;
        }
        Object oA = results[a];
        Object oB = results[b];
        if ((oA instanceof Exception) || (oB instanceof Exception)) {
            return false;
        }
        return !Objects.equals(oA, oB);
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
}

