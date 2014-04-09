package grader.trace.overall_transcript;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallTranscriptCleared extends OverallTranscriptInfo {
	String transcriptFileName;



public OverallTranscriptCleared(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, "", aFinder);
		transcriptFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getTranscriptFileName() {
	return transcriptFileName;
}



public void setTranscriptFileName(String transcriptFileName) {
	this.transcriptFileName = transcriptFileName;
}

	
	public static OverallTranscriptCleared newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, 
			String anFeatureManualFileName,
			Object aFinder) {
		String aMessage =  "Transcript Cleared from File:" + anFeatureManualFileName;
		OverallTranscriptCleared retVal = new OverallTranscriptCleared(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject,  anFeatureManualFileName,  aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
