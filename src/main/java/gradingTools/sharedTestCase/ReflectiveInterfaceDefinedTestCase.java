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
public class ReflectiveInterfaceDefinedTestCase extends BasicTestCase {

    private final String myName, tag, nameRegex, tagRegex;
    
    public ReflectiveInterfaceDefinedTestCase(String tag) {
        this(null, tag, ".*" + tag + ".*", ".*" + tag + ".*");
    }
    
    public ReflectiveInterfaceDefinedTestCase(String tag, String nameRegex, String tagRegex) {
        super("Interface tagged '" + tag + "'defined test case");
        this.myName = null;
        this.tag = tag;
        this.nameRegex = nameRegex;
        this.tagRegex = tagRegex;
    }
    
    public ReflectiveInterfaceDefinedTestCase(String name, String tag, String nameRegex, String tagRegex) {
        super(name == null ? "Interface tagged '" + tag + "'defined test case" : "Interface '" + name + "' defined test case");
        this.myName = name;
        this.tag = tag;
        this.nameRegex = nameRegex;
        this.tagRegex = tagRegex;
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        List<Class> classList = ProjectIntrospection.findInterfaces(project, myName, tag, nameRegex, tagRegex);
        if (classList.isEmpty()) {
            if (myName != null) {
                return fail("Interface '" + myName + "' not found");
            } else if (tag != null) {
                return fail("Interface tagged '" + tag + "' not found");
            } else {
                return fail("Interface not found");
            }
        } else {
            return pass();
        }
    }
    
}
