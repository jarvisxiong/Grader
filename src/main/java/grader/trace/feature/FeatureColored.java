package grader.trace.feature;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

import java.awt.Color;

public class FeatureColored extends GradingFeatureInfo {
	Color featureAutoNotesColor;



public FeatureColored(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			Color aColor,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureAutoNotesColor = aColor;
		// TODO Auto-generated constructor stub
	}

public Color getFeatureAutoNotesColor() {
	return featureAutoNotesColor;
}



public void setFeatureAutoNotesColor(Color featureAutoNotesColor) {
	this.featureAutoNotesColor = featureAutoNotesColor;
}

	
	public static FeatureColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			Color aColor,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "Colored:" + aColor;
		FeatureColored retVal = new FeatureColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aColor, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
