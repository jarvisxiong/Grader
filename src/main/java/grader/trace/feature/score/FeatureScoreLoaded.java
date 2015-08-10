package grader.trace.feature.score;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.feature.GradingFeatureInfo;
import grader.trace.steppers.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureScoreLoaded extends GradingFeatureInfo {
	String featureAutoScoreFileName;



public FeatureScoreLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String anOvervewFileName,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureAutoScoreFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoScoreFileName() {
	return featureAutoScoreFileName;
}



public void setFeatureAutoScoreFileName(String featureAutoScoreFileName) {
	this.featureAutoScoreFileName = featureAutoScoreFileName;
}

	
	public static FeatureScoreLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anFeatureAutoFileName,
			double aScore,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Score Loaded from File:" + anFeatureAutoFileName + ". Score:" + aScore;
		FeatureScoreLoaded retVal = new FeatureScoreLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anFeatureAutoFileName, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
