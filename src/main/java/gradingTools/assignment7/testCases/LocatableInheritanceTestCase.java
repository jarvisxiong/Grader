package gradingTools.assignment7.testCases;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.ClassDescription;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/14/13
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocatableInheritanceTestCase extends BasicTestCase {

    public LocatableInheritanceTestCase() {
        super("Locatable inheritance test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
Class<?> locatableSuperclass = BasicProjectIntrospection.findClass(project, "Locatable");//classDescription.get().getJavaClass();
        int methodCount = 0;
        int extendCount = 0;
        List<Class> errors = new ArrayList<>();
        for(ClassDescription desc : project.getClassesManager().get().getClassDescriptions()) {
            Class<?> clazz = desc.getJavaClass();
            boolean hasX = false;
            boolean hasY = false;
            for(Method m : clazz.getMethods()) {
                if (!hasX && m.getName().matches(".*[xX].*")) {
                    hasX = true;
                } else if (!hasY && m.getName().matches(".*[xY].*")) {
                    hasX = true;
                }
                if (hasX && hasY) {
                    methodCount ++;
                    break;
                }
            }
            if (hasX && hasY){
                if (locatableSuperclass.isAssignableFrom(clazz)) {
                    extendCount ++;
                } else {
                    errors.add(clazz);
                }
            }
        }
        if (methodCount == extendCount) {
            return pass();
        } else if (extendCount == 0) {
            return fail("No classes with X and Y properties extend Locatable");
        } else {
            StringBuilder sb = new StringBuilder(50);
            for(Class<?> c : errors) {
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                sb.append("Class '").append(c.toGenericString()).append("' should extend Locatable");
            }
            return partialPass(((double)extendCount) / methodCount, sb.toString());
        }        
// Make sure we can get the class description
//        if (project.getClassesManager().isEmpty())
//            throw new NotGradableException();
//        Option<ClassDescription> classDescription = new RootTagFinder(project).findClass("Locatable");
//        if (classDescription.isEmpty()) {
//            if (autoGrade)
//                throw new NotAutomatableException();
//            classDescription = ManualClassFinder.find(project, "Locatable");
//        }
//        Class<?> locatableSuperclass = classDescription.get().getJavaClass();
//
//        // There should be at least three classes tagged "locatable" (locatable, angle, bounded shape)
//        List<ClassDescription> locatables = project.getClassesManager().get().findClassByTag("Locatable");
//        if (locatables.size() < 3)
//            return fail("Expected more classes tagged \"Locatable\"", autoGrade);
//
//        // Make sure that everything that is tagged "locatable" extend the locatable class
//        int classCount = 0;
//        int correctClassCount = 0;
//        String notes = "";
//        for (ClassDescription description : locatables) {
//            classCount++;
//            if (ClassInheritanceChecker.isSubclass(description.getJavaClass(), locatableSuperclass))
//                correctClassCount++;
//            else
//                notes += "Class \"" + description.getJavaClass().getSimpleName() + "\" should extend Locatable. ";
//        }
//
//        return partialPass(correctClassCount / classCount, notes, autoGrade);
    }
}

