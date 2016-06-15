package grader.trace.file;

import grader.trace.GraderInfo;

public class FileInfo extends GraderInfo {
	
	String fileName;
	
	

	public FileInfo(String aMessage, String aFileName, Object aFinder) {
		super(aMessage, aFinder);
		fileName = aFileName;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
