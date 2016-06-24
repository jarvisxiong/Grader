package gradingTools.assignment7.testCases;

import java.util.List;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.util.ProjectIntrospection;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/14/13
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class BoundedShapeExtendsLocatableTestCase extends BasicTestCase {

    public BoundedShapeExtendsLocatableTestCase() {
        super("BoundedShape extends locatable test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        // Make sure we can get the class description
//        if (project.getClassesManager().isEmpty())
//            throw new NotGradableException();
//
//        // Get the bounded shape "root" class
//        Option<ClassDescription> boundedShapeDescription = new RootTagFinder(project).findClass("BoundedShape");
//        if (boundedShapeDescription.isEmpty()) {
//            if (autoGrade)
//                throw new NotAutomatableException();
//            boundedShapeDescription = ManualClassFinder.find(project, "BoundedShape");
//        }
//
//        // Get the locatable "root" class
//        Option<ClassDescription> locatableDescription = new RootTagFinder(project).findClass("Locatable");
//        if (locatableDescription.isEmpty()) {
//            if (autoGrade)
//                throw new NotAutomatableException();
//            locatableDescription = ManualClassFinder.find(project, "Locatable");
//        }

        Class<?> boundedClass = ProjectIntrospection.findClass(project, "BoundedShape");//boundedShapeDescription.get().getJavaClass();
        List<Class> lc = ProjectIntrospection.findClasses(project, "Locatable");
        for(int i = 0; i < lc.size(); i ++) {
            for(int j = 1; j < lc.size();) {
                if (lc.get(i).isAssignableFrom(lc.get(j))) {
                    lc.remove(j);
                } else if (lc.get(j).isAssignableFrom(lc.get(i))){
                    lc.remove(i);
                    i = 0;
                    j = 1;
                } else {
                    j++;
                }
            }
        }
        if (lc.isEmpty()) {
            return fail("Can't find Locatable class");
        }
        Class<?> locatableClass = lc.get(0);//IntrospectionUtil.findClass(project, "Locatable");//locatableDescription.get().getJavaClass();

        System.out.println("Locatable: " + locatableClass.toGenericString());
        System.out.println("BoundedShape: " + boundedClass.toGenericString());
        if (locatableClass.isAssignableFrom(boundedClass)) {//ClassInheritanceChecker.isSubclass(boundedClass, locatableClass))
            return pass();
        } else {
            return fail("BoundedShape ("+boundedClass.getSimpleName() + ") should extend Locatable (" + locatableClass.getSimpleName() + ")", autoGrade);
        }
    }
}

