package gradingTools.assignment8.testCases;

import util.models.PropertyListenerRegisterer;
import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/29/13
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ShapesRegisterListenerTestCase extends BasicTestCase {


    public ShapesRegisterListenerTestCase() {
        super("Shapes register event listeners test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        // Look for the classes that implement or interfaces that extend PropertyListenerRegisterer
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        ClassesManager manager = project.getClassesManager().get();
        int count = 0;
        for (ClassDescription description : manager.getClassDescriptions()) {
            if (PropertyListenerRegisterer.class.isAssignableFrom(description.getJavaClass()))
                count++;
        }

        // There should be at least two cases
        if (count >= 2)
            return pass(autoGrade);
        else
            return fail("All atomic shapes should extend/implement PropertyListenerRegisterer");
    }
}

