package grader.trace.transcript;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class TranscriptLoaded extends TranscriptInfo {
	String transcriptFileName;



public TranscriptLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		transcriptFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getTranscriptFileName() {
	return transcriptFileName;
}



public void setTranscriptFileName(String transcriptFileName) {
	this.transcriptFileName = transcriptFileName;
}

	
	public static TranscriptLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, 
			String anFeatureManualFileName,
			String aNotes,
			Object aFinder) {
		String aMessage =  "Transcript Loaded from File:" + anFeatureManualFileName + ". Transcript:" + aNotes;
		TranscriptLoaded retVal = new TranscriptLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject,  anFeatureManualFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
