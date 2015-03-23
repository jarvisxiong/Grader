package grader.trace.execution;

import util.trace.UncheckedTraceableException;
import grader.trace.execution.UserProcessExecutionStarted;

public class MainMethodNotFound extends UncheckedTraceableException{
	String mainClassName;
	String studentName;
	
	public MainMethodNotFound(String aMessage,
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
	
	public static MainMethodNotFound newCase(
			String aMainClassName, String aStudentName, Object aFinder) {
		String aMessage = "Main method in class : " + aMainClassName + " not found for student: " + aStudentName; 
				
		MainMethodNotFound retVal = new MainMethodNotFound(aMessage, aMainClassName, aStudentName, aFinder);
		retVal.announce();		
		return retVal;
	}

}
