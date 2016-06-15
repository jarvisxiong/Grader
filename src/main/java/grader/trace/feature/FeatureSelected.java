package grader.trace.feature;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class FeatureSelected extends GradingFeatureInfo {



public FeatureSelected(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,			
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static FeatureSelected newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "Selected.";
		FeatureSelected retVal = new FeatureSelected(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature,  aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
