package gradingTools.assignment6.testCases;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/7/13
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class ManualTestCase extends BasicTestCase {
    public ManualTestCase(String name) {
        super(name);
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotGradableException {
//        throw new NotGradableException();
        throw new NotGradableException("Manual test cannot be auto graded, why was it even tried?");

    }
}
