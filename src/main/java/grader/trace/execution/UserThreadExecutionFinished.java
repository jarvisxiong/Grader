package grader.trace.execution;

import grader.project.flexible.FlexibleProject;

import java.lang.reflect.Method;

import util.misc.Common;

public class UserThreadExecutionFinished extends UserThreadExecutionInfo{

	public UserThreadExecutionFinished(String aMessage, String aProjectName,
			String aMainClassName, FlexibleProject aProject, String[][] aMainArgs,
			String[] anOutputFiles, String[] anInputFiles, Method aMainMethod,
			Class aMainClass, Object aFinder) {
		super(aMessage, aProjectName, aMainClassName, aProject, aMainArgs,
				anOutputFiles, anInputFiles, aMainMethod, aMainClass, aFinder);
	}
	
	
	public static UserThreadExecutionFinished newCase (String aProjectName,
			String aMainClassName, FlexibleProject aProject, String[][] aMainArgs,
			String[] anOutputFiles, String[] anInputFiles, Method aMainMethod,
			Class aMainClass, Object aFinder) {
		String aMessage = "User thread ended; project: " + aProjectName +
				"main class: " + aMainClassName +
				"args:" + Common.toString(aMainArgs) +
				"input files: " + Common.toString(anInputFiles)+
				"output files: " + Common.toString(anOutputFiles);
		UserThreadExecutionFinished retVal = new UserThreadExecutionFinished(aMessage, aProjectName, aMainClassName, aProject, aMainArgs, anOutputFiles, anInputFiles, aMainMethod, aMainClass, aFinder);
		retVal.announce();
		return retVal;
				
		
	}
	
	
	
	

}
