package gradingTools.assignment9.testCases;

import java.awt.Graphics2D;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/6/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class PaintListenerPaintMethodTestCase extends BasicTestCase {

    public PaintListenerPaintMethodTestCase() {
        super("Paint Listener has a paint(Graphics2D) method");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        // Make sure we can get the class description
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();
        Set<ClassDescription> classDescriptions = project.getClassesManager().get().findByTag("PaintListener");
        if (classDescriptions.isEmpty())
            return fail("No class tagged \"PaintListener\"");
        ClassDescription classDescription = new ArrayList<>(classDescriptions).get(0);

//        try {
            boolean[] points = new boolean[3];
            for(Method m : classDescription.getJavaClass().getMethods()) {
                if (m.getName().matches(".*[pP]aint.*")) {
                    points[0] = true;
                    if (m.getParameterCount() > 0){
                        for(Class<?> clazz : m.getParameterTypes()) {
                            if (clazz.equals(Graphics2D.class)) {
                                points[1] = true;
                                break;
                            }
                        }
                        if (points[1] == true && m.getParameterCount() == 1) {
                            points[2] = true;
                            break;
                        }
                    }
                }
            }
//            classDescription.getJavaClass().getMethod("paint", Graphics2D.class);
            if (points[2]) {
                return pass();
            } else if (points[1]) {
                return partialPass(0.5, "Paint method found but without a Graphics2D parameter");
            } else {
                return fail("No paint method found");
            }
//        } catch (NoSuchMethodException e) {
//            return fail("No paint(Graphic2D) method found.", autoGrade);
//        }
    }
}

