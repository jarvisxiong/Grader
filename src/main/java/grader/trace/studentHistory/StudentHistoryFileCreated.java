package grader.trace.studentHistory;

import grader.basics.trace.FileInfo;

public class StudentHistoryFileCreated extends FileInfo {

	public StudentHistoryFileCreated(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static StudentHistoryFileCreated newCase(String aFileName,
			Object aFinder) {
		String aMessage =  "Student History File Created: " + aFileName;
		StudentHistoryFileCreated retVal = new StudentHistoryFileCreated(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
