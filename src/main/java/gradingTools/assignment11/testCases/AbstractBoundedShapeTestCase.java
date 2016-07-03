package gradingTools.assignment11.testCases;

import java.lang.reflect.Modifier;

import scala.Option;
import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.assignment11.tools.SpecialClassFinder;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/19/13
 * Time: 1:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractBoundedShapeTestCase extends BasicTestCase {
    public AbstractBoundedShapeTestCase() {
        super("Abstract bounded shape test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        Option<ClassDescription> boundedShape = SpecialClassFinder.getBoundedShape(project, autoGrade);

        if (boundedShape.isEmpty())
            return fail("Could not find the bounded shape class.", autoGrade);

        Class<?> _class = boundedShape.get().getJavaClass();
        if (Modifier.isAbstract(_class.getModifiers()))
            return pass(autoGrade);
        return fail("Bounded Shape is not abstract.", autoGrade);
    }
}

