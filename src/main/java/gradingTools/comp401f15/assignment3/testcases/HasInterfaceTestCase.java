package gradingTools.comp401f15.assignment3.testcases;

import java.lang.reflect.Method;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/8/13
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class HasInterfaceTestCase extends BasicTestCase {

    public HasInterfaceTestCase(String name) {
        super(name);
    }
    public HasInterfaceTestCase() {
    	super("Has interface");
    }
    
    public boolean hasInstanceMethod (Class aClass) {
    	Method[] aMethods = aClass.getDeclaredMethods();
    	for (Method aMethod:aMethods) {
    		if ((aMethod.getModifiers() & java.lang.reflect.Modifier.STATIC) == java.lang.reflect.Modifier.STATIC) 
    			continue;
    		else
    			return true;
    	}
    	return false;
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        // Look at all the classes
        String notes = "";
        int classCount = 0;
        int badCount = 0;
        Set<ClassDescription> classDescriptions = project.getClassesManager().get().getClassDescriptions();
        for (ClassDescription description : classDescriptions) {
            Class<?> _class = description.getJavaClass();
            if (!_class.isInterface()) {
//            	if (!hasInstanceMethod(_class))
//            		continue;
                Class<?>[] interfaces = _class.getInterfaces();
                classCount++;
                if (interfaces.length == 0 && 
                		hasInstanceMethod(_class)) {
                    badCount++;
                    notes += (notes.isEmpty() ? "" : "\n") + "Class has no interface: " + _class.getSimpleName();
                }
            }
        }

        double score = 1 -  ((double) badCount / classCount);
        return partialPass(score, notes, autoGrade);
    }
}

