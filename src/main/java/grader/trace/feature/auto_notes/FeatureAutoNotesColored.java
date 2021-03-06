package grader.trace.feature.auto_notes;

import java.awt.Color;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.SerializableStepperInfo;
import grader.trace.steppers.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

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
