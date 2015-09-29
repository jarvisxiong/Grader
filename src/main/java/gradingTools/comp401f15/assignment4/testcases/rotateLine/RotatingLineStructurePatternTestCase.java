package gradingTools.comp401f15.assignment4.testcases.rotateLine;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;
import java.lang.annotation.Annotation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

/**
 *
 * @author Andrew Vitkus
 */
public class RotatingLineStructurePatternTestCase extends BasicTestCase {
    private static final String TAG = "RotatingLine";
    private static final String[] classDescriptions = new String[]{null, TAG, ".*"+TAG+".*", ".*"+TAG+".*"};

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class aClass = IntrospectionUtil.findClass(project, 
                            classDescriptions[0],
                            classDescriptions[1],
                            classDescriptions[2],
                            classDescriptions[3]);
        if (aClass == null) {
            return fail("Cannot find rotating line class");
        }
        
        Annotation structurePattern = aClass.getAnnotation(StructurePattern.class);
        if (structurePattern == null) {
            return fail("Structure Pattern undefined");
        }
        if (structurePattern.toString().equals(StructurePatternNames.LINE_PATTERN)) {
            return pass();
        } else {
            return fail("Wrong Structure Pattern");
        }
    }
    
    public RotatingLineStructurePatternTestCase() {
        super("Rotating Line Class Structure Pattern Test Case");
    }
}
