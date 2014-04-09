package grader.trace.overall_score;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class SerializableOverallScoreInfo extends SerializableStepperInfo {
double score;



public SerializableOverallScoreInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		score = aScore;
		// TODO -generated constructor stub
	}

public double getScore() {
	return score;
}



public void setScore(double newVal) {
	this.score = newVal;
}

public static final int COLUMNS_USED = SerializableStepperInfo.COLUMNS_USED + 1; 


@Override
public String toCSVRow() {
	return super.toCSVRow() 
			+ "," + score;
}

public static String overallScoreFromCSVRow(String[] aRow) {
	return aRow[COLUMNS_USED -1];
}

	
//	public static SerializableScoreInfo newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			String aScore,
//			Object aFinder) {
//		String aMessage = "Overview Score ly Changed to:" + aScore;
//		SerializableScoreInfo retVal = new SerializableScoreInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
