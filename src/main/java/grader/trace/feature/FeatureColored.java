package grader.trace.feature;

import java.awt.Color;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

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
