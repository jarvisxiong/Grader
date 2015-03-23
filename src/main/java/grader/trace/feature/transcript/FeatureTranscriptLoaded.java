package grader.trace.feature.transcript;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureTranscriptLoaded extends FeatureTranscriptInfo {
	String transcriptFileName;



public FeatureTranscriptLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			GradingFeature aFeature,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aNotes, aFinder);
		transcriptFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getTranscriptFileName() {
	return transcriptFileName;
}



public void setTranscriptFileName(String transcriptFileName) {
	this.transcriptFileName = transcriptFileName;
}

	
	public static FeatureTranscriptLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anFeatureManualFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Transcript Loaded from File:" + anFeatureManualFileName + ". Notes:" + aNotes;
		FeatureTranscriptLoaded retVal = new FeatureTranscriptLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anFeatureManualFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
