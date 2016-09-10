package gradingTools.comp410s16.assignment5.testcases;

import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.ExecutionUtil;
import gradingTools.comp410s16.assignment5.EntryPair;
import gradingTools.comp410s16.assignment5.HeapInterface;
import org.apache.maven.plugin.lifecycle.Execution;
import scala.Option;
import wrappers.framework.project.ProjectWrapper;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by andrewwg94 on 3/7/16.
 */
public class FunctionalityTestCase extends BasicTestCase {
    public FunctionalityTestCase() {
        super("Functional Correctness");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        float score = 50;
        String message = "";


        Option<ClassesManager> classesManager = project.getClassesManager();
        Set<ClassDescription> names = classesManager.get().getClassDescriptions();
        System.out.println("class descriptions: " + names);
        String heapClassName="";
        for(ClassDescription d: names) {
            if(d.getSource().toString().toLowerCase().contains("heap")
                    &&!d.toString().toLowerCase().contains("interface")
                    &&!d.toString().toLowerCase().contains("assnbheap")) {
                heapClassName = d.toString();
            }
        }
        if(heapClassName.equals("")) return fail("Heap class not found");
        Class<?> heapClass = classesManager.get().findByClassName(heapClassName).get(0).getJavaClass();
        Class<?> pairClass = classesManager.get().findByClassName("EntryPair").get(0).getJavaClass();
        Class<?> pairArrayClass = Array.newInstance(pairClass, 0).getClass();

        Object heap = null;
        try {
            heap = heapClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method insert = null;
        try {
            insert = heapClass.getMethod("insert", pairClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Method getMin = null;
        try {
            getMin = heapClass.getMethod("getMin");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Method size = null;
        try {
            size = heapClass.getMethod("size");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Method delMin = null;
        try {
            delMin = heapClass.getMethod("delMin");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Method build = null;
        try {
            build = heapClass.getMethod("build", pairArrayClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Method getPriority = null;
        try {
            getPriority = pairClass.getMethod("getPriority");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        int[] inserts = {65, 16, 26, 13, 14, 19, 68, 20, 31, 21, 32};
        int[] ordered = {13, 14, 16, 19, 20, 21, 26, 31, 32, 65, 68};
        Object allPairs = Array.newInstance(pairClass, 11);

        for (int i = 1; i <= 11; i++) {
            Object aPair = null;
            try {
                aPair = pairClass.getConstructor(String.class, Integer.TYPE).newInstance("value-" + inserts[i - 1], inserts[i - 1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Array.set(allPairs, i - 1, aPair);
            try {
//                ExecutionUtil.timedInvokeWithExceptions(heap, insert, aPair);
                insert.invoke(heap,aPair);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int sizeVal = 0;
            try {
//                sizeVal = (int) ExecutionUtil.timedInvokeWithExceptions(heap, size);
                sizeVal = (int) size.invoke(heap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (sizeVal != i) {
                score -= 20;
                message += "insert or size not working properly.\n";
                break;
            }
            System.out.println("size: " + sizeVal);

        }
        for (int i = 0; i < 11; i++) {
            Object min = null;
            try {
//                min = ExecutionUtil.timedInvokeWithExceptions(heap, getMin);
                min = getMin.invoke(heap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object minValue = null;
            try {
                minValue = getPriority.invoke(min);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("min: " + minValue);
            if (minValue==null||!minValue.equals(ordered[i])) {
                score -= 20;
                message += "delMin or getMin not working properly.\n";
                break;
            }
            try {
//                ExecutionUtil.timedInvokeWithExceptions(heap, delMin);
                delMin.invoke(heap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Object heap2 = null;
        try {
            heap2 = heapClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[] param = {allPairs};
        try {
//            ExecutionUtil.timedInvokeWithExceptions(heap2, build, param);
            build.invoke(heap2,param);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 11; i++) {
            Object min = null;
            try {
//                min = ExecutionUtil.timedInvokeWithExceptions(heap2, getMin);
                min = getMin.invoke(heap2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object minValue = null;
            try {
                minValue = getPriority.invoke(min);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("min: " + minValue);
            if (minValue==null||!minValue.equals(ordered[i])) {
                score -= 10;
                message += "Build not working properly.";
                break;
            }
            try {
//                ExecutionUtil.timedInvokeWithExceptions(heap2, delMin);
                delMin.invoke(heap2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (score == 50) {
            return pass("All good");
        } else {
            System.out.println("score: " + score / 50);
            return partialPass(score / 50, message);
        }



    }
}
