package grader.trace.execution;

import framework.grading.testing.NotGradableException;
import grader.trace.CheckedGraderException;
import grader.trace.GraderInfo;

/**
 * This exception means that the student's program failed to run
 */
public class ProcessExecutionTimedOut extends  ProcessExecutionInfo {
	
	
	public ProcessExecutionTimedOut(String aMessage, String aFolderName,
			String anEntryPoint, 
			String aClassPath,
			Object aFinder)  {
		super(aMessage, aFolderName, anEntryPoint, aClassPath,  aFinder);
		// TODO Auto-generated constructor stub
	}

	
	public static ProcessExecutionTimedOut newCase(String aFolderName,
			String anEntryPoint, 
			String aClassPath,
			Object aFinder) {
		String aMessage = "Process timed out; folder: " + aFolderName + 
				" entry point: " + anEntryPoint + 
				" class path: " + aClassPath;
		ProcessExecutionTimedOut retVal = new ProcessExecutionTimedOut(aMessage, aFolderName, anEntryPoint,  aClassPath, aFinder);
		retVal.announce();		
		return retVal;
	}
}