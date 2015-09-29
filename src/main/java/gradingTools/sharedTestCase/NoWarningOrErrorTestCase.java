package gradingTools.sharedTestCase;

import java.util.ArrayList;
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


public class NoWarningOrErrorTestCase extends OutputAndErrorCheckingTestCase {
	protected String ignoreFilter;
	protected double penalty;
    public NoWarningOrErrorTestCase() {
        super("No warning test case");
    }
    public NoWarningOrErrorTestCase(String aName, String aFilter, double aPenalty) {
        super(aName);
        ignoreFilter = aFilter;
        penalty = aPenalty;
    }
    protected String ignoreFilter() {
    	return ignoreFilter;
    }
    public static boolean isErrorOrWarning (String aLine) {
    	return aLine.contains("E**") || aLine.contains("W**");
    }
    public  List<String> warningAndErrorLines(String anOutput) {
    	List<String> retVal = new ArrayList<>();
    	String[] anOutputLines = anOutput.split("\n");
    	String anIgnoreFilter = ignoreFilter();
    	for (String anOutputLine:anOutputLines) {
    		if (!isErrorOrWarning(anOutputLine) || (anIgnoreFilter != null && anOutputLine.matches(anIgnoreFilter)))
    			continue;
    		retVal.add(anOutputLine);
    		
    	}
    	return retVal;
    }
    
    protected double pointsPerErrorOrWarning() {
    	return penalty;
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
        		List<String> anErrorOrWarnings = warningAndErrorLines(anOutput);
        		double score = Math.max(0.0, 1 - (anErrorOrWarnings.size() * pointsPerErrorOrWarning()));
        		if (score == 1.0) {
        			return pass();
        		} else {
        			return partialPass(score, anErrorOrWarnings.toString());
        		}
//        		if (anOutput.contains("E**"))
//        			return fail("output contains errors");
//        		else if (anOutput.contains("W**"))
//        			return partialPass(0.5, "output contains warnings");
//        		else
//        			return pass();

        	}
//        	String aTranscript = aSakaiProject.getCurrentOutput().toString();
        	return pass();
        	
        	
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
}

