package grader.trace.execution;

import grader.basics.trace.GraderInfo;
import grader.project.flexible.FlexibleProject;

import java.lang.reflect.Method;

public class UserThreadExecutionInfo extends GraderInfo{
	  String projectName;
	    String mainClassName;
	    String[][] mainArgs;
	    FlexibleProject project;
	    String[] outputFiles;
	    String[] inputFiles;
	    Method mainMethod;
	    Class mainClass;
	
	public UserThreadExecutionInfo(String aMessage, 
			String aProjectName,
			String aMainClassName,
			FlexibleProject aProject,
		    String[][] aMainArgs,
		    String[] anOutputFiles,
		    String[] anInputFiles,
		    Method aMainMethod,
		    Class aMainClass,
			Object aFinder) {
		super(aMessage, aFinder);
		projectName = aProjectName;
		mainClassName = aMainClassName;
		mainArgs = aMainArgs;
		project = aProject;
		outputFiles = anOutputFiles;
		inputFiles = anInputFiles;
		mainMethod = aMainMethod;
		mainClass = aMainClass;
		
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMainClassName() {
		return mainClassName;
	}

	public void setMainClassName(String mainClassName) {
		this.mainClassName = mainClassName;
	}

	public String[][] getMainArgs() {
		return mainArgs;
	}

	public void setMainArgs(String[][] mainArgs) {
		this.mainArgs = mainArgs;
	}

	public FlexibleProject getProject() {
		return project;
	}

	public void setProject(FlexibleProject project) {
		this.project = project;
	}

	public String[] getOutputFiles() {
		return outputFiles;
	}

	public void setOutputFiles(String[] outputFiles) {
		this.outputFiles = outputFiles;
	}

	public String[] getInputFiles() {
		return inputFiles;
	}

	public void setInputFiles(String[] inputFiles) {
		this.inputFiles = inputFiles;
	}

	public Method getMainMethod() {
		return mainMethod;
	}

	public void setMainMethod(Method mainMethod) {
		this.mainMethod = mainMethod;
	}

	public Class getMainClass() {
		return mainClass;
	}

	public void setMainClass(Class mainClass) {
		this.mainClass = mainClass;
	}

	
	

}
