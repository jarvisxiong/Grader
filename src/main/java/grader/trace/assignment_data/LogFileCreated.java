package grader.trace.assignment_data;

import grader.trace.file.FileInfo;

public class LogFileCreated extends FileInfo {



public LogFileCreated(String aMessage,
			String anOvervewFileName,
			Object aFinder) {
		super(aMessage, anOvervewFileName, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static LogFileCreated newCase(
			String aFileName,
			Object aFinder) {
		String aMessage = "Onyen file created:" + aFileName;
		LogFileCreated retVal = new LogFileCreated(aMessage, aFileName, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
