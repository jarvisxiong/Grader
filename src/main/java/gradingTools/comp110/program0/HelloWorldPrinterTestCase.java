package gradingTools.comp110.program0;

import java.util.concurrent.TimeUnit;

import framework.execution.BasicRunningProject;
import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;

public class HelloWorldPrinterTestCase extends BasicTestCase {

    public HelloWorldPrinterTestCase() {
        super("Hello World printer test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {

            // Run the project with any input and collect the output
        	BasicRunningProject runningProject = project.launch("", 5);
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
