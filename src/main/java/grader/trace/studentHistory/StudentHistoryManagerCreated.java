package grader.trace.studentHistory;

import grader.basics.trace.FileInfo;
import grader.spreadsheet.csv.IndividualStudentHistoryManager;

public class StudentHistoryManagerCreated extends FileInfo {

	public StudentHistoryManagerCreated(String aMessage, String aFileName,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
	}
	
	public static StudentHistoryManagerCreated newCase(
			IndividualStudentHistoryManager aManager, 
			String aFileName,
			Object aFinder) {
		String aMessage =  "Student History File Created: " + aFileName;
		StudentHistoryManagerCreated retVal = new StudentHistoryManagerCreated(aMessage, aFileName, aFinder);
		retVal.announce();
		return retVal;
	}

}
