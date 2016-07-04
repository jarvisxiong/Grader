package grader.trace.sakai_bulk_folder;

import grader.basics.trace.GraderInfo;

import java.util.Collection;

public class StudentFolderNamesSorted extends GraderInfo{
	Collection<String> studentNames;

	
	public StudentFolderNamesSorted(String aMessage, Collection<String> aStudentNames, Object aFinder) {
		super(aMessage, aFinder);
		studentNames = aStudentNames;
	}

	public Collection<String> getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(Collection<String> studentNames) {
		this.studentNames = studentNames;
	}

	public static StudentFolderNamesSorted newCase(Collection<String> aStudentNames, 
			Object aFinder) {
		String aMessage =  "Student names sorted: " + aStudentNames;
		StudentFolderNamesSorted retVal = new StudentFolderNamesSorted(aMessage, aStudentNames, aFinder);
		retVal.announce();
		return retVal;
	}


}
