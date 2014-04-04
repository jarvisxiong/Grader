package grader.trace.stepper.overall_score;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallScoreManualChange extends SerializableOverallScoreInfo {
//String overallScore;



public OverallScoreManualChange(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		// TODO Auto-generated constructor stub
	}

//public String getOverallScore() {
//	return overallScore;
//}
//
//
//
//public void setOverallScore(String overallScore) {
//	this.overallScore = overallScore;
//}

//@Override
//public String toCSVRow() {
//	return super.toCSVRow() 
//			+ "," + overallScore;
//}

	
	public static OverallScoreManualChange newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			double aScore,
			Object aFinder) {
		String aMessage = "Overall Score Changed to:" + aScore;
		OverallScoreManualChange retVal = new OverallScoreManualChange(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
