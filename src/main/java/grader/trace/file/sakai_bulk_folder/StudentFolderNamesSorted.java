package grader.trace.file.sakai_bulk_folder;

import java.util.Set;

import grader.trace.GraderInfo;

public class StudentFolderNamesSorted extends GraderInfo{
	Set<String> studentNames;

	public StudentFolderNamesSorted(String aMessage, Set<String> aStudentNames, Object aFinder) {
		super(aMessage, aFinder);
		studentNames = aStudentNames;
	}
	
	public static StudentFolderNamesSorted newCase(Set<String> aStudentNames, 
			Object aFinder) {
		String aMessage =  "Student names sorted: " + aStudentNames;
		StudentFolderNamesSorted retVal = new StudentFolderNamesSorted(aMessage, aStudentNames, aFinder);
		retVal.announce();
		return retVal;
	}


}
