package gradingTools.assignment6.testCases;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import wrappers.framework.project.ProjectWrapper;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.Project;
import grader.project.flexible.FlexibleClassDescription;
import grader.sakai.project.SakaiProject;

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
    	SakaiProject sakaiProject = ((ProjectWrapper)project).getProject();
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        Set<String> packages = new HashSet<String>();
        List<FlexibleClassDescription> descriptions = sakaiProject.getClassesManager().getClassDescriptions();
        for (FlexibleClassDescription description : descriptions) {
            // Get the package
            packages.add(description.getPackageName());
        }

        if (packages.size() >= 3)
            return pass(autoGrade);
        else
            return fail("You only have " + packages.size() + " packages.", autoGrade);
    }
}

