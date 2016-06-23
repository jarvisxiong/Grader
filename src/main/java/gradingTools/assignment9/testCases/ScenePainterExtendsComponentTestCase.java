package gradingTools.assignment9.testCases;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/6/13
 * Time: 3:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScenePainterExtendsComponentTestCase extends BasicTestCase {

    public ScenePainterExtendsComponentTestCase() {
        super("Scene painter extends component test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        // Make sure we can get the class description
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();
        List<ClassDescription> classDescriptions = project.getClassesManager().get().findClassesAndInterfacesByTag("InheritingBridgeScenePainter");
        if (classDescriptions.isEmpty())
            return fail("No class tagged \"InheritingBridgeScenePainter\"");
        ClassDescription classDescription = new ArrayList<>(classDescriptions).get(0);

        boolean extendsComponent = Component.class.isAssignableFrom(classDescription.getJavaClass());
        if (extendsComponent)
            return pass();
        else
            return fail("The scene painter should extend Component.");
    }
}

