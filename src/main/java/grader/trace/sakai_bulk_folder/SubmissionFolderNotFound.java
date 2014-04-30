package grader.trace.sakai_bulk_folder;

import grader.trace.UncheckedGraderException;

public class SubmissionFolderNotFound extends UncheckedGraderException {
	String studentName;
	
	public SubmissionFolderNotFound(String aMessage, String aStudentName, Object aFinder) {
		super(aMessage, aFinder);
		studentName = aStudentName;
	}
	
	public static SubmissionFolderNotFound newCase(String aStudentName, Object aFinder) {
		String aMessage = "Submission folder not found for student:" + aStudentName;
		SubmissionFolderNotFound retVal = new SubmissionFolderNotFound(aMessage, aStudentName, aFinder);
		return retVal;
	}


}
