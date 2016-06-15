package grader.trace.feature.score;

import grader.assignment.GradingFeature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.feature.SerializableFeatureInfo;

public class SerializableFeatureScoreInfo extends SerializableFeatureInfo {
double featureScore;



public SerializableFeatureScoreInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		featureScore = aScore;
		// TODO -generated constructor stub
	}

public double getFeatureScore() {
	return featureScore;
}



public void setFeatureScore(double featureScore) {
	this.featureScore = featureScore;
}
static final int COLUMNS_USED = SerializableFeatureInfo.COLUMNS_USED + 1; 

@Override
public String toCSVRow() {
	return super.toCSVRow() 
			+ "," + featureScore;
}

public static double featureScoreFromCSVRow(String[] aRow) {
	return Double.parseDouble(aRow[COLUMNS_USED - 1]);
}

	
//	public static SerializableFeatureScoreInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject, GradingFeature aFeature,
//			String aScore,
//			Object aFinder) {
//		String aMessage = "Overview Score ly Changed to:" + aScore;
//		SerializableFeatureScoreInfo retVal = new SerializableFeatureScoreInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aScore, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
