package grader.trace.feature_auto_notes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureAutoNotesLoaded extends StepperInfo {
	String featureAutoNotesFileName;



public FeatureAutoNotesLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		featureAutoNotesFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoNotesFileName() {
	return featureAutoNotesFileName;
}



public void setFeatureAutoNotesFileName(String featureAutoNotesFileName) {
	this.featureAutoNotesFileName = featureAutoNotesFileName;
}

	
	public static FeatureAutoNotesLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anFeatureAutoFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature Auto Notes Loaded from File:" + anFeatureAutoFileName + ". Notes:" + aNotes;
		FeatureAutoNotesLoaded retVal = new FeatureAutoNotesLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anFeatureAutoFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
