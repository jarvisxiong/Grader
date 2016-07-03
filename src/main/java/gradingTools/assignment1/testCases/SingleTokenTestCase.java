package gradingTools.assignment1.testCases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.assignment1.FlexibleProgramRunner;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/7/13
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class SingleTokenTestCase extends BasicTestCase {

    public SingleTokenTestCase() {
        super("Single token test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
            FlexibleProgramRunner runner = new FlexibleProgramRunner(project, "12479");

            String output = runner.runWithSpaces();
            if (output.contains("12479"))
                return pass(autoGrade);
            output = runner.runNoSpaces();
            if (output.contains("12479"))
                return pass(autoGrade);
            return fail("Program should return a single token.", autoGrade);

        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

