package framework.execution;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import framework.project.Project;
import grader.sakai.project.SakaiProject;
import grader.trace.feature.transcript.FeatureTranscriptSaved;
import grader.trace.overall_transcript.OverallTranscriptSaved;
import util.trace.Tracer;
import wrappers.framework.project.ProjectWrapper;

/**
 * This is a wrapper for a running project independent of the method of
 * execution. This provides support for synchronization via semaphores and
 * output manipulation.
 */
public class RunningProject {

	private Semaphore runningState = new Semaphore(1);
	protected Map<String, String> processToOutput = new HashMap();
	protected Map<String, String> processToErrors = new HashMap();

	protected Map<String, String> processToOutputAndErrors = new HashMap();

	private String output = "";
	private String errorOutput = "";
	String outputAndErrors = "";
	private NotRunnableException exception;
//	Project project;
	ProjectWrapper projectWrapper;
	String outputFileName;
	StringBuffer projectOutput;
	SakaiProject project;

	public RunningProject(Project aProject) {
		exception = null;
		output = null;
		if (aProject != null && aProject instanceof ProjectWrapper) {
			projectWrapper = (ProjectWrapper) aProject;
			project = projectWrapper.getProject();
			outputFileName = project.getOutputFileName();
			projectOutput = project.getCurrentOutput();
		}
	}
	
	

	public void start() throws InterruptedException {
		runningState.acquire();
	}

	public void end() {
		runningState.release();
	}

	public void appendOutput(String newVal) {
		if (this.output == null && newVal != null) {
			this.output = "";
		} 
		this.output += newVal;
		
		outputAndErrors += newVal;
		
	}
	
	public void appendOutput(String aProcess, String newVal) {
		String processOutput = processToOutput.get(aProcess);
		if (processOutput == null && newVal != null) {
			processOutput = "";
		} 
		
		processOutput  += newVal;		
		processToOutput.put(aProcess, processOutput);
		appendErrorAndOutput(aProcess, newVal);		
	}
	public void appendErrorOutput(String aProcess, String newVal) {
		String processErrors = processToErrors.get(aProcess);
		if (processErrors == null && newVal != null) {
			processErrors = "";
		} 
		processErrors  += newVal;
		
		processToErrors.put(aProcess, processErrors);
		appendErrorAndOutput(aProcess, newVal);		
	}
	public void appendErrorAndOutput(String aProcess, String newVal) {
		String processOutputAndErrors = processToOutputAndErrors.get(aProcess);
		processOutputAndErrors += newVal;
		
		processToOutputAndErrors.put(aProcess, processOutputAndErrors);
		
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	public String getOutput() {
		return output;
	}
	
	public String getOutputAndErrors() {
		return outputAndErrors;
	}

	public void appendErrorOutput(String anErrorOutput) {
		if (this.errorOutput == null && anErrorOutput != null) {
			this.errorOutput = "";
		}
		this.errorOutput += anErrorOutput;
		outputAndErrors += anErrorOutput;

	}

	public void setErrorOutput(String errorOutput) {
		this.errorOutput = errorOutput;
	}

	public String getErrorOutput() {
		return errorOutput;
	}

	public void error() {
		this.exception = new NotRunnableException();
		exception.announce();
	}
	
	public static final String FEATURE_HEADER_PREFIX = "*****************************(";
	public static final String FEATURE_HEADER_SUFFIX = ")*****************************";

	
	
	public static String featureHeader(String aFeatureName) {
		return FEATURE_HEADER_PREFIX + aFeatureName + FEATURE_HEADER_SUFFIX ;
	}
	
	public static String extractFeatureTranscript(String aFeatureName, String allOutput) {
		int startIndex = allOutput.indexOf(featureHeader(aFeatureName));
		if (startIndex == -1)
			return "";
		int endIndex;
		int prevIndex = startIndex;
		int nextIndex;
		while (true) {
			nextIndex =  allOutput.indexOf(aFeatureName, prevIndex + 1);
			if (nextIndex < 0) {
				endIndex = allOutput.indexOf(FEATURE_HEADER_PREFIX, prevIndex + 1);
				if (endIndex == -1) 
					endIndex = allOutput.length();
				break;				
			} else {
				prevIndex = nextIndex;
			}
			
		}
		return allOutput.substring(startIndex, endIndex);
		
		
		
		
	}
	
	StringBuffer transcript = new StringBuffer(); // reusing the buffer
	
	String createFeatureTranscript() {
		transcript.setLength(0);
		if (project == null || project.getCurrentGradingFeature() == null) return "";
		String featureName = project.getCurrentGradingFeature().getName();

		transcript.append(featureHeader(featureName) + "\n");
//		transcript.append("*****************************(");
//		String featureName = project.getCurrentGradingFeature().getName();
//		transcript.append(featureName);
//		transcript.append(")*****************************\n");
		String input = project.getCurrentInput(); // this changes with process team, there can be multiple inputs and they can be given incrementally
		if (!input.isEmpty()) {
			transcript.append("INPUT(" + featureName + ")\n");
			transcript.append(input + "\n");
		}
		String[] args = project.getCurrentArgs();

		if (args.length > 0) {
			transcript.append("MAIN ARGS(" + featureName + ")\n");
			transcript.append("[");
			for (int i = 0; i < args.length; i++) {
				if (i != 0) {
					transcript.append(",");
				}
				transcript.append(args[i]);				
			}
			transcript.append("[");

		}
		if (output == null) {
			Tracer.error("Null output!");
			return "";
		}
		if (!output.isEmpty()) {
		transcript.append("OUTPUT(" + featureName + ")\n");
		transcript.append(output + "\n");
		}
		if (!errorOutput.isEmpty()) {
		transcript.append("ERRORS(" + featureName + ")\n");
		transcript.append(errorOutput + "\n");
		}
		return transcript.toString();

	}
	
	void appendCumulativeOutput() {
		if (projectOutput == null)
			return;
		String transcript = createFeatureTranscript();
		projectOutput.append(transcript);
		if (outputFileName == null)
			return;
		appendToTranscriptFile(project, transcript);
//		try {
//			FileWriter fileWriter = new FileWriter(outputFileName, true);
//			fileWriter.append(transcript);
//			OverallTranscriptSaved.newCase(null, null, project,  outputFileName, transcript, this);
////			if (project.getCurrentGradingFeature() != null)
////			FeatureTranscriptSaved.newCase(null, null, project,  project.getCurrentGradingFeature()., outputFileName, transcript, this);;
//			fileWriter.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	void appendToTranscriptFile(SakaiProject aProject, String aText) {
		try {
			String anOutputFileName = aProject.getOutputFileName();
			FileWriter fileWriter = new FileWriter(anOutputFileName, true);
			fileWriter.append(aText);
			OverallTranscriptSaved.newCase(null, null, aProject,  anOutputFileName, aText, this);
//			if (project.getCurrentGradingFeature() != null)
//			FeatureTranscriptSaved.newCase(null, null, project,  project.getCurrentGradingFeature()., outputFileName, transcript, this);;
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void appendOutputAndErrorsToTranscriptFile(SakaiProject aProject) {
		appendToTranscriptFile(aProject, getOutputAndErrors());
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
