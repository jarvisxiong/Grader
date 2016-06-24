package gradingTools.sharedTestCase;

import java.util.List;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ProjectIntrospection;

/**
 *
 * @author Andrew
 */
public class ReflectiveClassDefinedTestCase extends BasicTestCase {

    private final String myName, tag, nameRegex, tagRegex;
    
    public ReflectiveClassDefinedTestCase(String tag) {
        this(null, tag, ".*" + tag + ".*", ".*" + tag + ".*");
    }
    
    public ReflectiveClassDefinedTestCase(String tag, String nameRegex, String tagRegex) {
        super("Class tagged '" + tag + "'defined test case");
        this.myName = null;
        this.tag = tag;
        this.nameRegex = nameRegex;
        this.tagRegex = tagRegex;
    }
    
    public ReflectiveClassDefinedTestCase(String name, String tag, String nameRegex, String tagRegex) {
        super(name == null ? "Class tagged '" + tag + "'defined test case" : "Class '" + name + "' defined test case");
        this.myName = name;
        this.tag = tag;
        this.nameRegex = nameRegex;
        this.tagRegex = tagRegex;
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        List<Class> classList = ProjectIntrospection.findClasses(project, myName, tag, nameRegex, tagRegex);
        if (classList.isEmpty()) {
            if (myName != null) {
                return fail("Class '" + myName + "' not found");
            } else if (tag != null) {
                return fail("Class tagged '" + tag + "' not found");
            } else {
                return fail("Class not found");
            }
        } else {
            return pass();
        }
    }
    
}
