package grader.trace.feature.auto_result_format;

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

public class FeatureAutoResultFormatColored extends FeatureAutoResultFomatInfo {
	Color featureAutoResultsColor;



public FeatureAutoResultFormatColored(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			Color aColor,
			String aResults,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aResults, aFinder);
		featureAutoResultsColor = aColor;
		// TODO Auto-generated constructor stub
	}

public Color getFeatureAutoResultsColor() {
	return featureAutoResultsColor;
}



public void setFeatureAutoResultsColor(Color featureAutoResultsColor) {
	this.featureAutoResultsColor = featureAutoResultsColor;
}

	// step not currently performed
	public static FeatureAutoResultFormatColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			Color aColor,
			String aResults,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Results Colored:" + aColor;
		FeatureAutoResultFormatColored retVal = new FeatureAutoResultFormatColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aColor, aResults, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
