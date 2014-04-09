package grader.trace.sakai_bulk_folder;

import java.util.Collection;
import java.util.Set;

import grader.trace.GraderInfo;

public class StudentFolderNamesWrittenInOnyenFile extends GraderInfo{
	Collection<String> studentNames;
	String onyenFile;

	public StudentFolderNamesWrittenInOnyenFile(String aMessage, Collection<String> aStudentNames, String anOnyenFile, Object aFinder) {
		super(aMessage, aFinder);
		studentNames = aStudentNames;
		onyenFile = anOnyenFile;
	}
	
	
	
	public Collection<String> getStudentNames() {
		return studentNames;
	}



	public void setStudentNames(Collection<String> studentNames) {
		this.studentNames = studentNames;
	}



	public String getOnyenFile() {
		return onyenFile;
	}



	public void setOnyenFile(String onyenFile) {
		this.onyenFile = onyenFile;
	}



	public static StudentFolderNamesWrittenInOnyenFile newCase(Collection<String> aStudentNames, String anOnyenFile,
			Object aFinder) {
		String aMessage =  "Student names sorted: " + aStudentNames;
		StudentFolderNamesWrittenInOnyenFile retVal = new StudentFolderNamesWrittenInOnyenFile(aMessage, aStudentNames, anOnyenFile, aFinder);
		retVal.announce();
		return retVal;
	}


}
