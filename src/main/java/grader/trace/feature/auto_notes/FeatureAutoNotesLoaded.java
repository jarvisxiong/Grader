package grader.trace.feature.auto_notes;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.feature.GradingFeatureInfo;

public class FeatureAutoNotesLoaded extends GradingFeatureInfo {
	String featureAutoNotesFileName;



public FeatureAutoNotesLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
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
			SakaiProject aProject, GradingFeature aFeature,
			String anFeatureAutoFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Notes Loaded from File:" + anFeatureAutoFileName + ". Notes:" + aNotes;
		FeatureAutoNotesLoaded retVal = new FeatureAutoNotesLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anFeatureAutoFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
