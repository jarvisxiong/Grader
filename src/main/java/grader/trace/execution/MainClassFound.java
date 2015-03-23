package grader.trace.execution;

import grader.trace.GraderInfo;

public class MainClassFound extends GraderInfo{
	String mainClassName;
	String studentName;

	
	public MainClassFound(String aMessage, String aMainClassName, String aStudentName, Object aFinder) {
		super(aMessage, aFinder);
		mainClassName = aMainClassName;
		studentName = aStudentName;
	}

	public String getMainClassName() {
		return mainClassName;
	}

	public void setMainClassName(String mainClassName) {
		this.mainClassName = mainClassName;
	}

	public static MainClassFound newCase(String aMainClassName, String aStudentName, Object aFinder) {
		String aMessage = "Main class found: " + aMainClassName + " for student " + aStudentName;
		MainClassFound retVal = new MainClassFound(aMessage, aMainClassName, aStudentName, aFinder);
		retVal.announce();		
		return retVal;
	}

}
