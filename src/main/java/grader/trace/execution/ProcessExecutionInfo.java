package grader.trace.execution;

import grader.trace.GraderInfo;

public class ProcessExecutionInfo extends GraderInfo{
	String entryPoint;
	String classPath;
	String folderName;
	
	public ProcessExecutionInfo(String aMessage, 
			String aFolderName,
			String anEntryPoint, 
			String aClassPath,
			Object aFinder) {
		super(aMessage, aFinder);
		entryPoint = anEntryPoint;
		classPath = aClassPath;
		folderName = aFolderName;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	

}
