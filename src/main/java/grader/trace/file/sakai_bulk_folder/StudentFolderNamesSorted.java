package grader.trace.file.sakai_bulk_folder;

import java.util.Collection;
import java.util.Set;

import grader.trace.GraderInfo;

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
