package gradingTools.comp401f15.assignment10.testCases;

import java.util.Arrays;

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
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class SayCommandConstructorTestCase extends BasicTestCase {

    public SayCommandConstructorTestCase() {
        super("Say command object constructor test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();
        
        Class<?> sayCommandClass = BasicProjectIntrospection.findClass(project, null, "SayCommand", ".*[sS]ay[cC]ommand.*", ".*[sS]ay[cC]ommand.*");
        //Option<ClassDescription> classDescription = ClassFinder.get(project).findByTag("say command", autoGrade, ClassType.CLASS);
        if (sayCommandClass == null) { //classDescription.isEmpty())
            return fail("No say command object");
        }
        //Class<?> _class = classDescription.get().getJavaClass();

        // Find the avatar class and interface(s)
        Class<?> bridgeSceneClass = BasicProjectIntrospection.findClass(project, null, "BridgeScene", ".*[bB]ridge[sScene].*", ".*[bB]ridge[sScene].*");
        //Option<ClassDescription> sceneClassDescription = ClassFinder.get(project).findByTag("Bridge Scene", autoGrade, ClassType.CLASS);
        if (bridgeSceneClass == null) { //sceneClassDescription.isEmpty()) {
            return fail("No bridge scene class. This is needed for the constructor.");
        }
        //Class<?> sceneClass = sceneClassDescription.get().getJavaClass();
//        List<Class<?>> sceneClasses = Arrays.asList(bridgeSceneClass.getInterfaces());
//        sceneClasses.add(bridgeSceneClass);
        Class<?>[] sceneClasses = Arrays.copyOf(bridgeSceneClass.getInterfaces(), bridgeSceneClass.getInterfaces().length + 1);
        sceneClasses[sceneClasses.length - 1] = bridgeSceneClass;
        
        // Try both possible ordering of arguments with different classes.
        for (Class<?> scene : sceneClasses) {
            if (checkForConstructor(sayCommandClass, scene, String.class))
                return pass();
            if (checkForConstructor(sayCommandClass, String.class, scene))
                return pass();
        }
        return fail("No constructor taking a scene and a string.");
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

