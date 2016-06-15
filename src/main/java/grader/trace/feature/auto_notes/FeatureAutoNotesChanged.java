package grader.trace.feature.auto_notes;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class FeatureAutoNotesChanged extends FeatureAutoScoreInfo {
//String overallNotes;



public FeatureAutoNotesChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aNotes, aFinder);
		// TODO Auto-generated constructor stub
	}


	
	public static FeatureAutoNotesChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Notes Changed to:" + aNotes;
		FeatureAutoNotesChanged retVal = new FeatureAutoNotesChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature,aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
