package grader.trace.feature_manual_notes;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureManualNotesSaved extends FeatureManualNotesInfo {
	String featureManualNotesFileName;



public FeatureManualNotesSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aNotes, aFinder);
		featureManualNotesFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureManualNotesFileName() {
	return featureManualNotesFileName;
}



public void setFeatureManualNotesFileName(String featureManualNotesFileName) {
	this.featureManualNotesFileName = featureManualNotesFileName;
}

	
	public static FeatureManualNotesSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Manual Notes Saved to File:" + anOverviewFileName + ". Notes:" + aNotes;
		FeatureManualNotesSaved retVal = new FeatureManualNotesSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
