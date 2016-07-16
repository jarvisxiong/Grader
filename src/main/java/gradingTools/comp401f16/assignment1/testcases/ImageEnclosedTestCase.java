package gradingTools.comp401f16.assignment1.testcases;

import java.util.List;

import util.annotations.Explanation;
import wrappers.framework.project.ProjectWrapper;
import framework.grading.testing.BasicTestCase;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.GraderCommon;
import gradingTools.shared.testcases.OutputAndErrorCheckingTestCase;
// does not make sense to make it a Junit test case as it checks the student directory
// maybe need a better abstraction to get the student directory
public class ImageEnclosedTestCase extends BasicTestCase {

    public ImageEnclosedTestCase() {
        super("Image enclosed test case");
    }

   
    public static boolean hasImageDocument (List<String> aDocuments) {
		for (String aDocument:aDocuments) {
			if (GraderCommon.isImageDocument(aDocument))
				return true;
		}
		return false;
	};

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
        	SakaiProject aProject = ((ProjectWrapper) project).getProject();
        	List<String> aDocuments = aProject.getStudentAssignment().getDocuments();
        	if (hasImageDocument(aDocuments)) {
        		return pass();
        	} else {
        		return fail("Missing images");
        	}
        	
        	
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

