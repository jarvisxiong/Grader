/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradingTools.comp401f15.assignment7.testCases.commandInterpreter;

import java.lang.reflect.Method;

import util.annotations.Tags;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.BasicProjectIntrospection;

/**
 *
 * @author Andrew
 */
public class ErrorResilientCommandInterpreterDefinedTestCase extends BasicTestCase {
    
    public static final String COMMAND_INTERPETER_ERROR_METHOD = "COMMAND_INTERPRETER_ERROR_METHOD";

    public ErrorResilientCommandInterpreterDefinedTestCase() {
        super("Error Resilient Command Interpreter Test Case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        Class<?> commandInterpreterClass = BasicProjectIntrospection.findClass(project, null, "CommandInterpreter", ".*[cC]ommand.*[iI]nterpreter.*", ".*[cC]ommand[iI]nterpreter.*");
        if (commandInterpreterClass == null) {
            return fail("Can't find CommandInterpreter");
        }
        boolean tagged = false;
        Tags tag = commandInterpreterClass.getAnnotation(Tags.class);
        if (tag != null) {
            for(String s : tag.value()) {
                if (s.equals("ErrorResilient")) {
                    tagged = true;
                    break;
                }
            }
        }
        for(Method m : commandInterpreterClass.getMethods()) {
            //if (CharSequence.class.isAssignableFrom(m.getReturnType())) {
                if (m.getName().matches(".*get.*[eE]rror.*")) {
                    getCheckable().getRequirements().putUserObject(COMMAND_INTERPETER_ERROR_METHOD, m);
                    if (tagged) {
                        return pass();
                    } else {
                        return partialPass(0.75, "Error property found but untagged");
                    }
                }
            //}
        }
        if (tagged) {
            return fail("Tagged but no error property found. It may have missed by the autograder.");
        } else {
            return fail("Couldn't find an error property in CommandInterpreter class");
        }
    }
    
}
