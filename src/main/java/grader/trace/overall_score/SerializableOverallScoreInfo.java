package grader.trace.overall_score;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class SerializableOverallScoreInfo extends SerializableStepperInfo {
double featureAutoScore;



public SerializableOverallScoreInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		featureAutoScore = aScore;
		// TODO Auto-generated constructor stub
	}

public double getFeatureAutoScore() {
	return featureAutoScore;
}



public void setFeatureAutoScore(double featureAutoScore) {
	this.featureAutoScore = featureAutoScore;
}

@Override
public String toCSVRow() {
	return super.toCSVRow() 
			+ "," + featureAutoScore;
}

	
//	public static SerializableFeatureAutoScoreInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			String aScore,
//			Object aFinder) {
//		String aMessage = "Overview Score Autoly Changed to:" + aScore;
//		SerializableFeatureAutoScoreInfo retVal = new SerializableFeatureAutoScoreInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
