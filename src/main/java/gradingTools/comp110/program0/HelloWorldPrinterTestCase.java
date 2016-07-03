package gradingTools.comp110.program0;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class HelloWorldPrinterTestCase extends BasicTestCase {

    public HelloWorldPrinterTestCase() {
        super("Hello World printer test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {

            // Run the project with any input and collect the output
        	RunningProject runningProject = project.launch("", 5);
            String output = runningProject.await();

            // Now you can test the output for certain things
            if (output != null && output.trim().toLowerCase().contains("hello world"))
                return pass();
            else
                return fail("Did not print out Hello World");

        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}
