package gradingTools.comp401f16.assignment1.testcases;

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
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProductTest extends BasicTestCase {

    public ProductTest() {
        super("Product test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
            FlexibleProgramRunner runner = new FlexibleProgramRunner(project, "19 28");

            String output = runner.runWithSpaces();
            if (output.contains("532"))
                return pass(autoGrade);
            output = runner.runNoSpaces();
            if (output.contains("532"))
                return pass(autoGrade);
            return fail("Program should return the correctly computed product.", autoGrade);

        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

