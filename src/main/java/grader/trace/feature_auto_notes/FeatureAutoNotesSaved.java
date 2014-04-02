package grader.trace.feature_auto_notes;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureAutoNotesSaved extends FeatureAutoNotesInfo {
	String featureAutoNotesFileName;



public FeatureAutoNotesSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aNotes, aFinder);
		featureAutoNotesFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoNotesFileName() {
	return featureAutoNotesFileName;
}



public void setFeatureAutoNotesFileName(String featureAutoNotesFileName) {
	this.featureAutoNotesFileName = featureAutoNotesFileName;
}

	
	public static FeatureAutoNotesSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature Auto Notes Saved to File:" + anOverviewFileName + ". Notes:" + aNotes;
		FeatureAutoNotesSaved retVal = new FeatureAutoNotesSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
