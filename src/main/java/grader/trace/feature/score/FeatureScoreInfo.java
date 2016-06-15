package grader.trace.feature.score;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.feature.GradingFeatureInfo;

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
