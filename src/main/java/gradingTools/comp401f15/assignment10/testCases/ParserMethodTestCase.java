package gradingTools.comp401f15.assignment10.testCases;

import java.lang.reflect.Method;
import java.util.List;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/11/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParserMethodTestCase extends BasicTestCase {

    private String tag;
    private String regexTag;

    public ParserMethodTestCase(String tag) {
        super(tag + " creates cmd object test case");
        this.tag = tag;
        this.regexTag = ".*" + tag + ".*";
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        // Get the command interpreter
        Class<?> commandInterpreterClass = BasicProjectIntrospection.findClass(project, null, "CommandInterpreter", ".*[cC]ommand[iI]nterpreter.*", ".*[cC]ommand[iI]nterpreter.*");
        //Option<ClassDescription> classDescription = ClassFinder.get(project).findByTag("Command Interpreter", autoGrade, ClassType.CLASS);
        if (commandInterpreterClass == null) { //classDescription.isEmpty())
            return fail("Looking for method in command interpreter, but the class was not found.");
        }
        
        List<Method> methods = BasicProjectIntrospection.findMethod(commandInterpreterClass, null, tag, regexTag, regexTag); //classDescription.get().getTaggedMethods(tag);
        if (methods.isEmpty()) {
            return fail("Couldn't find the " + tag + " method");
        }
        
        if (Runnable.class.isAssignableFrom(methods.get(0).getReturnType())) {
            return pass();
        } else {
            return fail(tag + " method should return a Runnable");
        }
    }
}

