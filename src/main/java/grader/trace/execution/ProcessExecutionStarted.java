package grader.trace.execution;

import framework.grading.testing.NotGradableException;
import grader.trace.CheckedGraderException;
import grader.trace.GraderInfo;

/**
 * This exception means that the student's program failed to run
 */
public class ProcessExecutionStarted extends  ProcessExecutionInfo {
	
	
	public ProcessExecutionStarted(String aMessage, String aFolderName,
			String anEntryPoint, 
			String aClassPath,
			Object aFinder)  {
		super(aMessage, aFolderName, anEntryPoint, aClassPath,  aFinder);
		// TODO Auto-generated constructor stub
	}

	
	public static ProcessExecutionStarted newCase(String aFolderName,
			String anEntryPoint, 
			String aClassPath,
			Object aFinder) {
		String aMessage = "Process started; folder: " + aFolderName + 
				" entry point: " + anEntryPoint + 
				" class path: " + aClassPath;
		ProcessExecutionStarted retVal = new ProcessExecutionStarted(aMessage, aFolderName, anEntryPoint,  aClassPath, aFinder);
		retVal.announce();		
		return retVal;
	}
}