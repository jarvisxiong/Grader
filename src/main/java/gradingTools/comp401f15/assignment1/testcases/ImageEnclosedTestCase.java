package gradingTools.comp401f15.assignment1.testcases;

import java.util.List;

import wrappers.framework.project.ProjectWrapper;
import framework.execution.NotRunnableException;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.OutputAndErrorCheckingTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.GraderCommon;
import gradingTools.assignment1.FlexibleProgramRunner;


public class ImageEnclosedTestCase extends OutputAndErrorCheckingTestCase {

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

