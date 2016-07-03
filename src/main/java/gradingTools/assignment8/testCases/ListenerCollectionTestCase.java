package gradingTools.assignment8.testCases;

import java.io.IOException;

import org.apache.commons.io.FileUtils;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.Project;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 10/29/13
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class ListenerCollectionTestCase extends BasicTestCase {

    public ListenerCollectionTestCase() {
        super("Shapes have listener collections test case");
    }

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        if (project.getClassesManager().isEmpty())
            throw new NotGradableException();

        // Look for collections of PropertyChangeListener. There should be at least one.
        // PropertyChangeListener[], List<PropertyChangeListener>, Set<PropertyChangeListener>
        for (ClassDescription description : project.getClassesManager().get().getClassDescriptions()) {
            try {
                String source = FileUtils.readFileToString(description.getSource());
                if (source.contains("PropertyChangeListener[]") || source.contains("List<PropertyChangeListener>") ||
                        source.contains("Set<PropertyChangeListener>"))
                    return pass();
            } catch (IOException e) {
                throw new NotGradableException();
            }
        }

        return fail("No PropertyChangeListener collections found.");
    }
}

