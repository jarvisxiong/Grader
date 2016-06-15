package grader.trace.feature.manual_notes;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class FeatureManualNotesLoaded extends FeatureManualNotesInfo {
	String featureManualNotesFileName;



public FeatureManualNotesLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			GradingFeature aFeature,
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

	
	public static FeatureManualNotesLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anFeatureManualFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Manual Notes Loaded from File:" + anFeatureManualFileName + ". Notes:" + aNotes;
		FeatureManualNotesLoaded retVal = new FeatureManualNotesLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anFeatureManualFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
