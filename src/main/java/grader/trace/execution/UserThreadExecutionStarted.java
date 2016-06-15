package grader.trace.execution;

import grader.project.Project;

import java.lang.reflect.Method;

import util.misc.Common;

public class UserThreadExecutionStarted extends UserThreadExecutionInfo{

	public UserThreadExecutionStarted(String aMessage, String aProjectName,
			String aMainClassName, Project aProject, String[][] aMainArgs,
			String[] anOutputFiles, String[] anInputFiles, Method aMainMethod,
			Class aMainClass, Object aFinder) {
		super(aMessage, aProjectName, aMainClassName, aProject, aMainArgs,
				anOutputFiles, anInputFiles, aMainMethod, aMainClass, aFinder);
	}
	
	
	public static UserThreadExecutionStarted newCase (String aProjectName,
			String aMainClassName, Project aProject, String[][] aMainArgs,
			String[] anOutputFiles, String[] anInputFiles, Method aMainMethod,
			Class aMainClass, Object aFinder) {
		String aMessage = "User thread started; project: " + aProjectName +
				"main class: " + aMainClassName +
				"args:" + Common.toString(aMainArgs) +
				"input files: " + Common.toString(anInputFiles)+
				"output files: " + Common.toString(anOutputFiles);
		UserThreadExecutionStarted retVal = new UserThreadExecutionStarted(aMessage, aProjectName, aMainClassName, aProject, aMainArgs, anOutputFiles, anInputFiles, aMainMethod, aMainClass, aFinder);
		retVal.announce();
		return retVal;
				
		
	}
	
	
	
	

}
