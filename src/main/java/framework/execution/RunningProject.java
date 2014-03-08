package framework.execution;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import framework.project.Project;
import grader.sakai.project.SakaiProject;
import wrappers.framework.project.ProjectWrapper;

/**
 * This is a wrapper for a running project independent of the method of
 * execution. This provides support for synchronization via semaphores and
 * output manipulation.
 */
public class RunningProject {

	private Semaphore runningState = new Semaphore(1);
	private String output = "";
	private String errorOutput = "";
	private NotRunnableException exception;
//	Project project;
	ProjectWrapper projectWrapper;
	String outputFileName;
	StringBuffer projectOutput;

	public RunningProject(Project aProject) {
		exception = null;
		output = null;
		if (aProject instanceof ProjectWrapper) {
			projectWrapper = (ProjectWrapper) aProject;
			outputFileName = projectWrapper.getProject().getOutputFileName();
			projectOutput = projectWrapper.getProject().getCurrentOutput();
		}
	}

	public void start() throws InterruptedException {
		runningState.acquire();
	}

	public void end() {
		runningState.release();
	}

	public void appendOutput(String output) {
		if (this.output == null && output != null) {
			this.output = "";
		}
		this.output += output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public void appendErrorOutput(String errorOutput) {
		if (this.errorOutput == null && errorOutput != null) {
			this.errorOutput = "";
		}
		this.errorOutput += errorOutput;
	}

	public void setErrorOutput(String errorOutput) {
		this.errorOutput = errorOutput;
	}

	public String getErrorOutput() {
		return errorOutput;
	}

	public void error() {
		this.exception = new NotRunnableException();
	}
	
	void appendCumulativeOutput() {
		if (projectOutput == null)
			return;
		projectOutput.append(output);
		if (outputFileName == null)
			return;
		try {
			FileWriter fileWriter = new FileWriter(outputFileName, true);
			fileWriter.append(output);
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String await() throws NotRunnableException {
		if (exception != null)
			throw exception;
		try {
			runningState.acquire();
		} catch (InterruptedException e) {
			throw new NotRunnableException();
		}
		appendCumulativeOutput();
		return output;
	}

}
