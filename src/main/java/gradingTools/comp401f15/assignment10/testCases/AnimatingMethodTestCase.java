package gradingTools.comp401f15.assignment10.testCases;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.util.IntrospectionUtil;
import scala.Option;
import tools.classFinder2.ClassFinder;

import java.lang.reflect.Method;
import java.util.List;
import tools.classFinder2.ClassType;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/12/13
 * Time: 12:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnimatingMethodTestCase extends BasicTestCase {

    private String tag;
    private String regexTag;

    public AnimatingMethodTestCase(String tag) {
        super(tag + " animating method test case");
        this.tag = tag;
        this.regexTag = ".*" + tag + ".*";
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        // Get the command interpreter
        Class<?> commandInterpreterClass = IntrospectionUtil.findClass(project, null, "CommandInterpreter", ".*[cC]ommand[iI]nterpreter.*", ".*[cC]ommand[iI]nterpreter.*");
        //Option<ClassDescription> classDescription = ClassFinder.get(project).findByTag("Command Interpreter", autoGrade, ClassType.CLASS);
        if (commandInterpreterClass == null) { //classDescription.isEmpty())
            return fail("Looking for method in command interpreter, but the class was not found.");
        }
        
        // Get the method
        List<Method> methods = IntrospectionUtil.findMethod(commandInterpreterClass, null, tag, regexTag, regexTag); //classDescription.get().getTaggedMethods(tag);
        if (methods.isEmpty()) {
            return fail("No method tagged: " + tag);
        }

        // Check that it's parameterless
        if (methods.get(0).getParameterCount() == 0) {
            return pass();
        } else {
            return partialPass(0.5, tag + " method should be parameterless");
        }
    }
}

