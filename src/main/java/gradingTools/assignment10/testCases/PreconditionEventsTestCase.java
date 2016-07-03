package gradingTools.assignment10.testCases;

import javax.swing.JOptionPane;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/12/13
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class PreconditionEventsTestCase extends BasicTestCase {
    public PreconditionEventsTestCase() {
        super("Precondition events test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getTraceableLog().getLog().isEmpty()) {
            if (!autoGrade)
                JOptionPane.showMessageDialog(null, "Please run (not launch) the program before running this check.", "Execution results needed", JOptionPane.PLAIN_MESSAGE);
            throw new NotAutomatableException();
        }



        // Search the log for events
        for (Exception e : project.getTraceableLog().getLog())
            System.out.println(e);

        return pass(autoGrade);
    }
}

