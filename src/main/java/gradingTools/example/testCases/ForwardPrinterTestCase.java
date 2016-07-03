package gradingTools.example.testCases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class ForwardPrinterTestCase extends BasicTestCase {

    public ForwardPrinterTestCase() {
        super("Forward printer test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {

            // This is how you can run a project with any input and collect the output
        	RunningProject runningProject = project.launch("Hello world\n");
            String output = runningProject.await();

            // Now you can test the output for certain things
            if (output.contains("Hello world"))
                return pass(autoGrade);
            else
                return fail("Did not print out normally", autoGrade);

        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

