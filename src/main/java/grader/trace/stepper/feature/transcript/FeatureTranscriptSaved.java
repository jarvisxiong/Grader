package grader.trace.stepper.feature.transcript;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureTranscriptSaved extends FeatureTranscriptInfo {
	String TranscriptFileName;



public FeatureTranscriptSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aNotes, aFinder);
		TranscriptFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getTranscriptFileName() {
	return TranscriptFileName;
}



public void setTranscriptFileName(String TranscriptFileName) {
	this.TranscriptFileName = TranscriptFileName;
}

	// feature transcript is not saved individually currently, so this step does not happens
	public static FeatureTranscriptSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Transcript Saved to File:" + anOverviewFileName + ". Notes:" + aNotes;
		FeatureTranscriptSaved retVal = new FeatureTranscriptSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
