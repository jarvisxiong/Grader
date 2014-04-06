package grader.trace.file.sakai_bulk_folder.student;

import grader.trace.CheckedGraderException;

public class SubmissionFolderNotFound extends CheckedGraderException {
	String studentName;
	
	public SubmissionFolderNotFound(String aMessage, String aStudentName, Object aFinder) {
		super(aMessage, aFinder);
		studentName = aStudentName;
	}
	
	public static SubmissionFolderNotFound newCase(String aStudentName, Object aFinder) {
		String aMessage = "Student folder not found for student:" + aStudentName;
		SubmissionFolderNotFound retVal = new SubmissionFolderNotFound(aMessage, aStudentName, aFinder);
		return retVal;
	}


}
