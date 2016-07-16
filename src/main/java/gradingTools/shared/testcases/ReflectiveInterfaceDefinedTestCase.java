package gradingTools.shared.testcases;

import java.util.Set;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

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
        Set<Class> classList = BasicProjectIntrospection.findInterfaces(project, myName, tag, nameRegex, tagRegex);
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
