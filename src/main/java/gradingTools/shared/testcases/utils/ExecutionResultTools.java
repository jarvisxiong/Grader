package gradingTools.shared.testcases.utils;

import gradingTools.sharedTestCase.MethodExecutionTestCase.ExecutionData;

import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Andrew Vitkus
 */
public class ExecutionResultTools {
    
    public static boolean checkIsException(ExecutionData[] results, int a) {
        return checkIsInstance(results, a, Exception.class);
    }
    
    public static boolean checkIsException(Object[] results, int a) {
        return checkIsInstance(results, a, Exception.class);
    }
    
    public static boolean checkNotException(ExecutionData[] results, int a) {
        return !checkIsInstance(results, a, Exception.class);
    }
    
    public static boolean checkNotException(Object[] results, int a) {
        return !checkIsInstance(results, a, Exception.class);
    }
    
    
    public static boolean checkNEqual(ExecutionData[] results, int a, int b) {
        return checkEqualValue(results, a, b) == 4;
    }
    
    public static boolean checkNEqual(Object[] results, int a, int b) {
        return checkEqualValue(results, a, b) == 4;
    }
    
    public static boolean checkNEqual(ExecutionData[] results, int a, Object value) {
        if (outOfRange(results, a)) {
            return false;
        }
        return !checkEqual(results[a].getRetVal(), value);
    }
    
    public static boolean checkNEqual(Object[] results, int a, Object value) {
        if (outOfRange(results, a)) {
            return false;
        }
        
        return !checkEqual(results[a], value);
    }
    
    public static boolean checkEqual(ExecutionData[] results, int a, int b) {
        return checkEqualValue(results, a, b) == 0;
    }
    
    public static boolean checkEqual(Object[] results, int a, int b) {
        return checkEqualValue(results, a, b) == 0;
    }
    
    public static boolean checkEqual(ExecutionData[] results, int a, Object value) {
        if (outOfRange(results, a)) {
            return false;
        }
        return checkEqual(results[a].getRetVal(), value);
    }
    
    public static boolean checkEqual(Object[] results, int a, Object value) {
        if (outOfRange(results, a)) {
            return false;
        }
        
        return checkEqual(results[a], value);
    }
    
    private static boolean checkEqual(Object a, Object b) {
        return Objects.equals(a, b);
    }
    
    public static boolean checkLT(ExecutionData[] results, int a, int b) {
        return checkLTValue(results, a, b) == 0;
    }
    
    public static boolean checkLT(Object[] results, int a, int b) {
        return checkLTValue(results, a, b) == 0;
    }
    
    public static boolean checkLTE(ExecutionData[] results, int a, int b) {
        return checkGTValue(results, a, b) == 4;
    }
    
    public static boolean checkLTE(Object[] results, int a, int b) {
        return checkGTValue(results, a, b) == 4;
    }
    
    public static boolean checkGT(ExecutionData[] results, int a, int b) {
        return checkGTValue(results, a, b) == 0;
    }
    
    public static boolean checkGT(Object[] results, int a, int b) {
        return checkGTValue(results, a, b) == 0;
    }
    
    public static boolean checkGTE(ExecutionData[] results, int a, int b) {
        return checkLTValue(results, a, b) == 4;
    }
    
    public static boolean checkGTE(Object[] results, int a, int b) {
        return checkLTValue(results, a, b) == 4;
    }
    
    public static boolean checkIsInstance(ExecutionData[] results, int a, Class<?> c) {
        if (outOfRange(results, a)) {
            return false;
        }
        return checkIsInstance(results[a].getRetVal(), c);
    }
    
    public static boolean checkIsInstance(Object[] results, int a, Class<?> c) {
        if (outOfRange(results, a)) {
            return false;
        }
        return checkIsInstance(results[a], c);
    }
    
    public static boolean checkNotInstance(ExecutionData[] results, int a, Class<?> c) {
        if (outOfRange(results, a)) {
            return false;
        }
        return !checkIsInstance(results[a].getRetVal(), c);
    }
    
    public static boolean checkNotInstance(Object[] results, int a, Class<?> c) {
        if (outOfRange(results, a)) {
            return false;
        }
        return !checkIsInstance(results[a], c);
    }
    
    private static boolean checkIsInstance(Object o, Class<?> c) {
        return c.isInstance(o);
    }
    
    public static int checkEqualValue(ExecutionData[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, 0);
    }
    
    public static int checkEqualValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, 0);
    }
    
    public static int checkLTValue(ExecutionData[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, -1);
    }
    
    public static int checkLTValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, -1);
    }
    
    public static int checkGTValue(ExecutionData[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, 1);
    }
    
    public static int checkGTValue(Object[] resutls, int a, int b) {
        return checkCompare(resutls, a, b, 1);
    }
    
    public static int checkCompare(ExecutionData[] results, int a, int b, int sign) {
        if (outOfRange(results, a) || outOfRange(results, b)) {
            return 1;
        }
        return checkCompare(results[a].getRetVal(), results[b].getRetVal(), sign);
    }
    
    public static int checkCompare(Object[] results, int a, int b, int sign) {
        if (outOfRange(results, a) || outOfRange(results, b)) {
            return 1;
        }
        return checkCompare(results[a], results[b], sign);
    }
    
    private static int checkCompare(Object oA, Object oB, int sign) {
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
    
    private static boolean outOfRange(Object[] arr, int index) {
        return index >= arr.length;
    }
    
    public static Optional<Object> getValue(ExecutionData[] arr, int index) {
        if (outOfRange(arr, index)) {
            return Optional.empty();
        }
        return Optional.ofNullable(arr[index].getRetVal());
    }
    
    public static Optional<Object> getValue(Object[] arr, int index) {
        if (outOfRange(arr, index)) {
            return Optional.empty();
        }
        return Optional.ofNullable(arr[index]);
    }
    
    public static Optional<Integer> getIntValue(ExecutionData[] arr, int index) {
        if (outOfRange(arr, index)) {
            return Optional.empty();
        }
        Object data = arr[index].getRetVal();
        if (data instanceof Integer) {
            return Optional.of((Integer)data);
        } else {
            return Optional.empty();
        }
    }
    
    public static Optional<Integer> getIntValue(Object[] arr, int index) {
        if (outOfRange(arr, index)) {
            return Optional.empty();
        }
        Object data = arr[index];
        if (data instanceof Integer) {
            return Optional.of((Integer)data);
        } else {
            return Optional.empty();
        }
    }
    
    public static int countBoolean(boolean[] arr, boolean value) {
        int count = 0;
        for(boolean bool : arr) {
            if (bool == value) {
                count ++;
            }
        }
        return count;
    }
}
