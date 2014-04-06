package grader.trace.execution;

import grader.trace.CheckedTraceableException;
import grader.trace.execution.UserProcessExecutionStarted;

public class MainClassNotFound extends CheckedTraceableException{
	String mainClassName;
	String studentName;
	
	public MainClassNotFound(String aMessage,
			String aMainClassName, String aStudentName, Object aFinder) {
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
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public static MainClassNotFound newCase(
			String aMainClassName, String aStudentName, Object aFinder) {
		String aMessage = "Main class name: " + aMainClassName + " not found for student: " + aStudentName; 
				
		MainClassNotFound retVal = new MainClassNotFound(aMessage, aMainClassName, aStudentName, aFinder);
		retVal.announce();		
		return retVal;
	}

}
