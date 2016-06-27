package gradingTools.comp401f15.assignment5.testcases.shapes;

import java.lang.reflect.Constructor;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.BasicProjectIntrospection;

/**
 *
 * @author Andrew Vitkus
 */
public class AngleShapeDefinedTestCase extends BasicTestCase {
    private static final String TAG = "Angle";
    private static final String[] classDescriptions = new String[]{null, TAG, ".*"+TAG+".*", ".*"+TAG+".*"};

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class aClass = BasicProjectIntrospection.findClass(project, 
                            classDescriptions[0],
                            classDescriptions[1],
                            classDescriptions[2],
                            classDescriptions[3]);
        if (aClass == null) {
            return fail("Cannot find angle shape class");
        } else {
            Constructor[] constructors = aClass.getConstructors();
            for(Constructor c : constructors) {
                if (c.getParameterCount() == 0) {
                    return pass();
                }
            }
            return partialPass(0.5, "Missing default constructor");
        }
    }
    
    public AngleShapeDefinedTestCase() {
        super("Angle Shape Class Properly Defined Test Case");
    }
}
