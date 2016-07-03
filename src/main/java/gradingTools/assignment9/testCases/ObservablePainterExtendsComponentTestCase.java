package gradingTools.assignment9.testCases;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/6/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObservablePainterExtendsComponentTestCase extends BasicTestCase {

    public ObservablePainterExtendsComponentTestCase() {
        super("Observable bridge scene painter extends component test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        // Make sure we can get the class description
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();
        Set<ClassDescription> classDescriptions = project.getClassesManager().get().findByTag("ObservableBridgeScenePainter");
        if (classDescriptions.isEmpty())
            return fail("No class tagged \"ObservableBridgeScenePainter\"");
        ClassDescription classDescription = new ArrayList<>(classDescriptions).get(0);

        boolean extendsComponent = Component.class.isAssignableFrom(classDescription.getJavaClass());
        if (extendsComponent)
            return pass();
        else
            return fail("The observable bridge scene painter should extend Component.");
    }
}

