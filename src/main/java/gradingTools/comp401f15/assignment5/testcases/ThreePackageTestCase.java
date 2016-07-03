package gradingTools.comp401f15.assignment5.testcases;

import java.util.HashSet;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/7/13
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ThreePackageTestCase extends BasicTestCase {
    public ThreePackageTestCase(String name) {
        super(name);
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        Set<String> packages = new HashSet<String>();
        Set<ClassDescription> descriptions = project.getClassesManager().get().getClassDescriptions();
        for (ClassDescription description : descriptions) {
            // Get the package
            packages.add(description.getJavaClass().getPackage().getName());
        }

        if (packages.size() >= 3)
            return pass(autoGrade);
        else
            return fail("You only have " + packages.size() + " packages.", autoGrade);
    }
}

