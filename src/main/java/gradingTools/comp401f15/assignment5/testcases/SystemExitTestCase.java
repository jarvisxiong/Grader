package gradingTools.comp401f15.assignment5.testcases;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import tools.CodeTools;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/8/13
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class SystemExitTestCase extends BasicTestCase {
    public SystemExitTestCase(String name) {
        super(name);
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
                // Fail if we find a System.exit
                if (code.contains("System.exit("))
                    return fail("System.exut() found in " + description.getJavaClass().getSimpleName(), autoGrade);
            } catch (IOException e) {}
        }
        return pass(autoGrade);
    }
}

