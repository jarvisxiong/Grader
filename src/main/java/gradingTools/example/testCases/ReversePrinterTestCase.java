package gradingTools.example.testCases;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class ReversePrinterTestCase extends BasicTestCase {

    public ReversePrinterTestCase() {
        super("Forward printer test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {

        	RunningProject runningProject = project.launch("Hello world\n");
            String output = runningProject.await();
            if (output.contains("dlrow olleH"))
                return pass(autoGrade);
            else
                return fail("Did not print out backward", autoGrade);

        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

