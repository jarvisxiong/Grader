package grader.trace.feature.auto_notes;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

import java.awt.Color;

public class FeatureAutoNotesColored extends FeatureAutoNotesInfo {
	Color featureAutoNotesColor;



public FeatureAutoNotesColored(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			Color aColor,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aNotes, aFinder);
		featureAutoNotesColor = aColor;
		// TODO Auto-generated constructor stub
	}

public Color getFeatureAutoNotesColor() {
	return featureAutoNotesColor;
}



public void setFeatureAutoNotesColor(Color featureAutoNotesColor) {
	this.featureAutoNotesColor = featureAutoNotesColor;
}

	// this step is not currently implemented, auto notes are not colored
	public static FeatureAutoNotesColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			Color aColor,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Notes Colored:" + aColor;
		FeatureAutoNotesColored retVal = new FeatureAutoNotesColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aColor, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
