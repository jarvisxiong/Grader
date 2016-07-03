package examples.checkers.testCases;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/6/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class PassingTestCase extends BasicTestCase {
    public PassingTestCase() {
        super("Passing test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        return pass(autoGrade);
    }
}

