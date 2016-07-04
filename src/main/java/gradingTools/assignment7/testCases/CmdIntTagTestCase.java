package gradingTools.assignment7.testCases;

import java.util.Set;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/22/13
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class CmdIntTagTestCase extends BasicTestCase {


    public CmdIntTagTestCase() {
        super("Command interpreter tag test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        Set<ClassDescription> classes = project.getClassesManager().get().findByTag("Command Interpreter");
        if (classes.size() == 1)
            return pass(autoGrade);
        return fail("There should be one class tagged \"Command Interpreter\"", autoGrade);
    }
}

