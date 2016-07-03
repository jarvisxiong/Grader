package gradingTools.assignment9.testCases;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/29/13
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class ScenePainterTagTestCase extends BasicTestCase {
    public ScenePainterTagTestCase() {
        super("Scene painter tag test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        if (project.getClassesManager().get().findByTag("InheritingBridgeScenePainter").isEmpty())
            return fail("No class found with tag \"InheritingBridgeScenePainter\"");
        else
            return pass();
    }
}

