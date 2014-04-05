package grader.trace.stepper.feature.score;

import java.awt.Color;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import grader.trace.stepper.feature.FeatureInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureScoreColored extends FeatureScoreInfo {
	Color featureAutoScoreColor;



public FeatureScoreColored(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			Color aColor,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aScore, aFinder);
		featureAutoScoreColor = aColor;
		// TODO Auto-generated constructor stub
	}

public Color getFeatureAutoScoreColor() {
	return featureAutoScoreColor;
}



public void setFeatureAutoScoreColor(Color featureAutoScoreColor) {
	this.featureAutoScoreColor = featureAutoScoreColor;
}

	
	public static FeatureScoreColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			Color aColor,
			double aScore,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Score Colored:" + aColor;
		FeatureScoreColored retVal = new FeatureScoreColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aColor, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
