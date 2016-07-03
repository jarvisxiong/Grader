package gradingTools.assignment7.testCases;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/14/13
 * Time: 12:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TableTagTestCase extends BasicTestCase {

    public TableTagTestCase() {
        super("Table tag test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        // There should be one class tagged with "Table"
        if (project.getClassesManager().get().findByTag("Table").size() == 1)
            return pass(autoGrade);
        else
            return fail("There should be one class tagged with \"Table\".", autoGrade);
    }
}

