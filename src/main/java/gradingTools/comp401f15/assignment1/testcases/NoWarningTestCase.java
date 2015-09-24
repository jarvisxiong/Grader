package gradingTools.comp401f15.assignment1.testcases;

import java.util.List;

import wrappers.framework.project.ProjectWrapper;
import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.OutputAndErrorCheckingTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.util.GraderCommon;
import gradingTools.assignment1.FlexibleProgramRunner;


public class NoWarningTestCase extends OutputAndErrorCheckingTestCase {

    public NoWarningTestCase() {
        super("No warning test case");
    }

   
    RunningProject lastRunningProject;

    @Override
    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
        try {
        	if (!autoGrade)
        		return fail("No graded in auto run");
        	SakaiProject aSakaiProject = ((ProjectWrapper) project).getProject();
        	RunningProject aRunner = aSakaiProject.getCurrentRunningProject();
        	if (aRunner == null ) {
        		System.err.println("No project run interactively");
        	} else if (aRunner == lastRunningProject) {
        		System.err.println("Have already graded this interactive run");
        	} else {
        		lastRunningProject = aRunner;
        		String anOutput = lastRunningProject.getOutput();
        		if (anOutput.contains("E**"))
        			return fail("output contains errors");
        		else if (anOutput.contains("W**"))
        			return partialPass(0.5, "output contains warnings");
        		else
        			return pass();

        	}
//        	String aTranscript = aSakaiProject.getCurrentOutput().toString();
        	return pass();
        	
        	
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

