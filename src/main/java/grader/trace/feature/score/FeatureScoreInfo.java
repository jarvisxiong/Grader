package grader.trace.feature.score;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.feature.GradingFeatureInfo;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureScoreInfo extends GradingFeatureInfo {
double featureScore;



public FeatureScoreInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureScore = aScore;
		// TODO Auto-generated constructor stub
	}

public double getFeatureScore() {
	return featureScore;
}



public void setFeatureScore(double featureScore) {
	this.featureScore = featureScore;
}

//@Override
//public String toCSVRow() {
//	return super.toCSVRow() 
//			+ "," + featureAutoScore;
//}

	
//	public static SerializableFeatureAutoScoreInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject, GradingFeature aFeature,
//			String aScore,
//			Object aFinder) {
//		String aMessage = "Overview Score Autoly Changed to:" + aScore;
//		SerializableFeatureAutoScoreInfo retVal = new SerializableFeatureAutoScoreInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aScore, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
