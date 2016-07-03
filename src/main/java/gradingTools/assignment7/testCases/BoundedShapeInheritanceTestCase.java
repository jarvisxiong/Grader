package gradingTools.assignment7.testCases;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.BasicProjectIntrospection;
import grader.basics.project.ClassDescription;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/14/13
 * Time: 10:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class BoundedShapeInheritanceTestCase extends BasicTestCase {

    public BoundedShapeInheritanceTestCase() {
        super("Locatable inheritance test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        // Make sure we can get the class description
//        if (project.getClassesManager().isEmpty())
//            throw new NotGradableException();
//        Option<ClassDescription> classDescription = new RootTagFinder(project).findClass("BoundedShape");
//        if (classDescription.isEmpty()) {
//            if (autoGrade)
//                throw new NotAutomatableException();
//            classDescription = ManualClassFinder.find(project, "BoundedShape");
//        }
        Class<?> boundedShapeSuperclass = BasicProjectIntrospection.findClass(project, "BoundedShape");//classDescription.get().getJavaClass();
        int methodCount = 0;
        int extendCount = 0;
        List<Class> errors = new ArrayList<>();
        for(ClassDescription desc : project.getClassesManager().get().getClassDescriptions()) {
            Class<?> clazz = desc.getJavaClass();
            boolean hasWidth = false;
            boolean hasHeight = false;
            for(Method m : clazz.getMethods()) {
                if (!hasWidth && m.getName().matches(".*[wW]idth.*")) {
                    hasWidth = true;
                } else if (!hasHeight && m.getName().matches(".*[hH]eight.*")) {
                    hasWidth = true;
                }
                if (hasWidth && hasHeight) {
                    methodCount ++;
                    break;
                }
            }
            if (hasWidth && hasHeight){
                if (boundedShapeSuperclass.isAssignableFrom(clazz)) {
                    extendCount ++;
                } else {
                    errors.add(clazz);
                }
            }
        }
        if (methodCount == extendCount) {
            return pass();
        } else if (extendCount == 0) {
            return fail("No classes with height and width properties extend BoundedShape");
        } else {
            StringBuilder sb = new StringBuilder(50);
            for(Class<?> c : errors) {
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                sb.append("Class '").append(c.toGenericString()).append("' should extend BoundedShape");
            }
            return partialPass(((double)extendCount) / methodCount, sb.toString());
        }
        
        
        // There should be at least three classes tagged "bounded shape" (bounded shape, line, image)
//        List<ClassDescription> locatables = project.getClassesManager().get().findClassByTag("BoundedShape");
//        if (locatables.size() < 3)
//            return fail("Expected more classes tagged \"BoundedShape\"", autoGrade);
//
//        // Make sure that everything that is tagged "bounded shape" extend the locatable class
//        int classCount = 0;
//        int correctClassCount = 0;
//        String notes = "";
//        for (ClassDescription description : locatables) {
//            classCount++;
//            if (ClassInheritanceChecker.isSubclass(description.getJavaClass(), boundedShapeSuperclass))
//                correctClassCount++;
//            else
//                notes += "Class \"" + description.getJavaClass().getSimpleName() + "\" should extend BoundedShape. ";
//        }
//
//        return partialPass(correctClassCount / classCount, notes, autoGrade);
    }
}

