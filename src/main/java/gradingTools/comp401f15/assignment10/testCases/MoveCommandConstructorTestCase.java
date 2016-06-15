package gradingTools.comp401f15.assignment10.testCases;

import java.util.Arrays;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.IntrospectionUtil;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/11/13
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class MoveCommandConstructorTestCase extends BasicTestCase {

    public MoveCommandConstructorTestCase() {
        super("Move command object constructor test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty()) {
            throw new NotGradableException();
        }

        Class<?> moveCommandClass = IntrospectionUtil.findClass(project, null, "MoveCommand", ".*[mM]ove[cC]ommand.*", ".*[mM]ove[cC]ommand.*");
        //Option<ClassDescription> classDescription = ClassFinder.get(project).findByTag("MoveCommand", autoGrade, ClassType.CLASS);
        if (moveCommandClass == null) {//classDescription.isEmpty()) {
            return fail("No move command object");
        }
        //Class<?> _class = classDescription.get().getJavaClass();

        // Find the avatar class and interface(s)
        Class<?> avatarClass = IntrospectionUtil.findClass(project, null, "Avatar", ".*[aA]vatar.*", ".*[aA]vatar.*");
        //Option<ClassDescription> avatarClassDescription = ClassFinder.get(project).findByTag("Avatar", autoGrade, ClassType.CLASS);
        if (avatarClass == null) { //avatarClassDescription.isEmpty()) {
            return fail("No single avatar class. This is needed for the constructor.");
        }
        //Class<?> avatarClass = avatarClassDescription.get().getJavaClass();
        
        Class<?>[] avatarClasses = Arrays.copyOf(avatarClass.getInterfaces(), avatarClass.getInterfaces().length + 1);
        avatarClasses[avatarClasses.length - 1] = avatarClass;

        // Try all three possible ordering of arguments with different classes.
        for (Class<?> avatar : avatarClasses) {
            if (checkForConstructor(moveCommandClass, avatar, int.class, int.class)) 
                return pass();
            if (checkForConstructor(moveCommandClass, avatar, Integer.class, Integer.class))
                return pass();
            if (checkForConstructor(moveCommandClass, int.class, avatar, int.class))
                return pass();
            if (checkForConstructor(moveCommandClass, Integer.class, avatar, Integer.class))
                return pass();
            if (checkForConstructor(moveCommandClass, int.class, int.class, avatar))
                return pass();
            if (checkForConstructor(moveCommandClass, Integer.class, Integer.class, avatar))
                return pass();
        }
        return fail("No constructor taking 1 avatar and 2 ints.");
    }

    private boolean checkForConstructor(Class<?> _class, Class<?> ... argTypes) {
        try {
            _class.getConstructor(argTypes);
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }
}

