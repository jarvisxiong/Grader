package grader.trace.feature.score;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureScoreSaved extends FeatureScoreInfo {
	String featureAutoScoreFileName;



public FeatureScoreSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String anOvervewFileName,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aScore, aFinder);
		featureAutoScoreFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getFeatureAutoScoreFileName() {
	return featureAutoScoreFileName;
}



public void setFeatureAutoScoreFileName(String featureAutoScoreFileName) {
	this.featureAutoScoreFileName = featureAutoScoreFileName;
}

	
	public static FeatureScoreSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String anOverviewFileName,
			double aScore,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Score Saved to File:" + anOverviewFileName + ". Score:" + aScore;
		FeatureScoreSaved retVal = new FeatureScoreSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, anOverviewFileName, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
