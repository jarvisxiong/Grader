package gradingTools.comp401f15.assignment11.testcases;

import java.lang.reflect.Modifier;

import scala.Option;
import tools.classFinder2.ClassFinder;
import tools.classFinder2.ClassType;
import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/19/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractAncestorTokenTestCase extends BasicTestCase {


    public AbstractAncestorTokenTestCase() {
        super("Abstract ancestor token test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        Option<ClassDescription> description = ClassFinder.get(project).findByTag("ancestor token", autoGrade, ClassType.ABSTRACT);
        if (description.isEmpty())
            return fail("No ancestor token class", autoGrade);

        Class<?> _class = description.get().getJavaClass();
        if (Modifier.isAbstract(_class.getModifiers()))
            return pass(autoGrade);
        return fail("Ancestor token class is not abstract", autoGrade);
    }
}

