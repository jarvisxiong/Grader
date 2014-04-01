package grader.trace.feature_manual_notes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureManualNotesLoaded extends StepperInfo {
	String featureManualNotesFileName;



public FeatureManualNotesLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		featureManualNotesFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureManualNotesFileName() {
	return featureManualNotesFileName;
}



public void setFeatureManualNotesFileName(String featureManualNotesFileName) {
	this.featureManualNotesFileName = featureManualNotesFileName;
}

	
	public static FeatureManualNotesLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anFeatureManualFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature Manual Notes Loaded from File:" + anFeatureManualFileName + ". Notes:" + aNotes;
		FeatureManualNotesLoaded retVal = new FeatureManualNotesLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anFeatureManualFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
