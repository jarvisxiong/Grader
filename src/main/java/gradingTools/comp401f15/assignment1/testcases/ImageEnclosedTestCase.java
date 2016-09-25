package gradingTools.comp401f15.assignment1.testcases;

import java.util.List;

import wrappers.framework.project.ProjectWrapper;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.GraderCommon;
import gradingTools.sharedTestCase.OldOutputAndErrorCheckingTestCase;


public class ImageEnclosedTestCase extends OldOutputAndErrorCheckingTestCase {

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

