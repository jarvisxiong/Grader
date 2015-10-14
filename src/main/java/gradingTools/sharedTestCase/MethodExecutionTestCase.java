package gradingTools.sharedTestCase;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ExecutionUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew Vitkus
 */
public class MethodExecutionTestCase extends BasicTestCase {
    
    private final Constructor constructor;
    private final Object[] constructorArgs;
    private final Object executionTarget;
    private final Method[] methods;
    private final Object[][] arguments;
    private final Object[] expectedReturnValues;
    
    public MethodExecutionTestCase(String name, Object o, MethodEnvironment me, Object retVals) {
        this(name, null, null, o, new Method[]{me.getMethod()}, new Object[][]{me.getArguments()}, new Object[]{retVals});
    }
    
    public MethodExecutionTestCase(String name, Object o, Method m, Object[] args, Object retVals) {
        this(name, null, null, o, new Method[]{m}, new Object[][]{args}, new Object[]{retVals});
    }
    
    public MethodExecutionTestCase(String name, Object o, MethodEnvironment[] meArr, Object retVals) {
        this(name, null, null, o,
                Arrays.stream(meArr).map((me)->me.getMethod()).toArray(Method[]::new),
                Arrays.stream(meArr).map((me)->me.getArguments()).toArray(Object[][]::new),
                new Object[]{retVals});
    }
    
    public MethodExecutionTestCase(String name, Object o, Method[] m, Object[][] args, Object[] retVals) {
        this(name, null, null, o, m, args, retVals);
    }
    
    public MethodExecutionTestCase(String name, Constructor c, Object[] cArgs, MethodEnvironment me, Object retVals) {
        this(name, c, cArgs, null, new Method[]{me.getMethod()}, new Object[][]{me.getArguments()}, new Object[]{retVals});
    }
    
    public MethodExecutionTestCase(String name, Constructor c, Object[] cArgs, Method m, Object[] args, Object retVals) {
        this(name, c, cArgs, null, new Method[]{m}, new Object[][]{args}, new Object[]{retVals});
    }
    
    public MethodExecutionTestCase(String name, Constructor c, Object[] cArgs, Method[] m, Object[][] args, Object[] retVals) {
        this(name, c, cArgs, null, m, args, retVals);
    }
    
    public MethodExecutionTestCase(String name, Constructor c, Object[] cArgs, MethodEnvironment[] meArr, Object[] retVals) {
        this(name, c, cArgs, null,
                Arrays.stream(meArr).map((me)->me.getMethod()).toArray(Method[]::new),
                Arrays.stream(meArr).map((me)->me.getArguments()).toArray(Object[][]::new),
                new Object[]{retVals});
    }
    
    private MethodExecutionTestCase(String name, Constructor c, Object[] cArgs, Object o, Method[] m, Object[][] args, Object[] retVals) {
        super(name);
        constructor = c;
        constructorArgs = cArgs;
        executionTarget = o;
        methods = m;
        arguments = args;
        expectedReturnValues = retVals;
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Object[] details;
        if (constructor == null) {
            details = invoke(executionTarget, methods, arguments);
        } else {
            details = invoke(constructor, constructorArgs, methods, arguments);
        }
        if (details == null || details.length == 0) {
            return fail("Couldn't grade or find problems, something is very wrong"); 
        }
        int passed = 0;
        String errors = "";
        for(int i = 0; i < details.length; i ++) {
            if (details[i] instanceof Exception) {
                errors += errorToString(constructor, methods[i], (Exception)details[i]) + "\n";
            } else {
                if (expectedReturnValues[i] == null) {
                    if (details[i] == null) {
                        passed ++;
                    } else {
                        errors += errorToString(constructor, methods[i], null) + "\n";
                    }
                } else if (expectedReturnValues[i].equals(details[i])) {
                    passed ++;
                } else {
                    errors += errorToString(constructor, methods[i], null) + "\n";
                }
            }
        }
        if (passed == methods.length) {
            return pass();
        } else if (passed == 0) {
            return fail(errors);
        } else {
            return partialPass(((double)passed) / methods.length, errors);
        }
    }
    
    private static Object[] invoke(Constructor c, Object[] cArgs, MethodEnvironment[] meArr) {
        Method[] mArr = Arrays.stream(meArr).map((me)->me.getMethod()).toArray(Method[]::new);
        Object[][] pArr = Arrays.stream(meArr).map((me)->me.getArguments()).toArray(Object[][]::new);
        return invoke(c, cArgs, mArr, pArr);
    }
    
    private static Object[] invoke(Object o, MethodEnvironment[] meArr) {
        Method[] mArr = Arrays.stream(meArr).map((me)->me.getMethod()).toArray(Method[]::new);
        Object[][] pArr = Arrays.stream(meArr).map((me)->me.getArguments()).toArray(Object[][]::new);
        return invoke(o, mArr, pArr);
    }
    
    private static Object[] invoke(Constructor c, Object[] cArgs, Method m[], Object[]... arguments) {
        Object o;
        try {
            o = ExecutionUtil.timedInvokeWithExceptions(c, cArgs);
            return invoke(o, m, arguments);
        } catch (InstantiationException ex) {
            return new Object[]{ex};
        } catch (Exception e) {
            return new Object[]{new ExecutionFailureException(e)};
        }
    }
    
    private static Object[] invoke(Object o, Method m[], Object[]... arguments) {
        Object[] ret;
        if (m.length == arguments.length) {
            ret = new Object[m.length];
            for(int i = 0; i < m.length; i ++) {
                ret[i] = invoke(o, m[i], arguments[i]);
            }
        } else {
            ret = new Object[0];
        }
        return ret;
    }
    
    private static Object invoke(Constructor c, Object[] cArgs, MethodEnvironment me) {
        return invoke(c, cArgs, me.getMethod(), me.getArguments());
    }
    
    private static Object invoke(Object o, MethodEnvironment me) {
        return invoke(o, me.getMethod(), me.getArguments());
    }
    
    private static Object invoke(Constructor c, Object[] cArgs, Method m, Object... arguments) {
        Object o;
        try {
            o = ExecutionUtil.timedInvokeWithExceptions(c, cArgs);
            return invoke(o, m, arguments);
        } catch (InstantiationException ex) {
            return ex;
        } catch (Exception e) {
            return new ExecutionFailureException(e);
        }
    }
    
    private static Object invoke(Object o, Method m, Object... arguments) {
        try {
            return ExecutionUtil.timedInvokeWithExceptions(o, m, arguments);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return ex;
        } catch (Exception e) {
            return new ExecutionFailureException(e);
        }
    }
    
    private static String errorToString(Constructor c, Method m, Exception e) {
        String message = "Error running method '" + m.toGenericString()
                + "' from class '" + m.getDeclaringClass().getTypeName() + "': ";
        if (e == null) {
            message += "Wrong output";
        } else {
            if (e instanceof ClassNotFoundException) {
                message += "Can't find class";
            } else if (e instanceof NoSuchMethodException) {
                message += "Can't find method";
            } else if (e instanceof IllegalAccessException) {
                message += "Can't access method";
            } else if (e instanceof IllegalArgumentException) {
                message += "Argument type missmatch";
            } else if (e instanceof InvocationTargetException) {
                message += "Can't invoke method";
            } else if (e instanceof InstantiationException) {
                message += "Can't execute constructor '" + c.toGenericString() + "'";
            } else if (e instanceof ExecutionFailureException) {
                message += "Method threw exception - " + e.getCause().getLocalizedMessage();
            } else {
                message += "Unexpected exception - " + e.getLocalizedMessage();
            }
        }
        return message;
    }
    
    public static class MethodEnvironment {
        private final Method m;
        private final Object[] arguments;

        private MethodEnvironment(Method m, Object[] arguments) {
            this.m = m;
            this.arguments = arguments;
        }
        
        public static MethodEnvironment get(Method m, Object... arguments) {
            return new MethodEnvironment(m, arguments);
        }

        public Method getMethod() {
            return m;
        }

        public Object[] getArguments() {
            return arguments;
        }
        
    }
    
    static class ExecutionFailureException extends Exception {

        private ExecutionFailureException(Exception e) {
            super(e);
        }
        
    }
}
