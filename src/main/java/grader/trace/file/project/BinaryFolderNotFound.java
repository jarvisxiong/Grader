package grader.trace.file.project;

import grader.trace.CheckedGraderException;

public class BinaryFolderNotFound extends CheckedGraderException {
	String projectFolder;
	
	

	public BinaryFolderNotFound(String aMessage, String aFileName, Object aFinder) {
		super(aMessage, aFinder);
		projectFolder = aFileName;
	}
	
	public String getProjectFolder() {
		return projectFolder;
	}

	public void setProjectFolder(String projectFolder) {
		this.projectFolder = projectFolder;
	}
	
	public static BinaryFolderNotFound newCase(String aFileName, Object aFinder) {
		String aMessage = "Binary  folder not found in project folder:" + aFileName;
		BinaryFolderNotFound retVal = new BinaryFolderNotFound(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}


}
