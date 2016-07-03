package gradingTools.assignment9.testCases;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import tools.CodeTools;
import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/6/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class RefreshTestCase extends BasicTestCase {

    public RefreshTestCase() {
        super("No .refresh() test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        Set<ClassDescription> classDescriptions = project.getClassesManager().get().getClassDescriptions();
        for (ClassDescription description : classDescriptions) {
            try {

                // Get the source code as a string and remove comments
                String code = FileUtils.readFileToString(description.getSource());
                code = CodeTools.removeComments(code);

                // Fail if we find a refresh method
                if (code.contains(".refresh();"))
                    return fail("Object editor refresh() call found in " + description.getJavaClass().getSimpleName(), autoGrade);

            } catch (IOException e) {}
        }
        return pass(autoGrade);
    }
}

