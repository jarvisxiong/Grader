package grader.trace.stepper.multiplier;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class MultiplierAutoChange extends MultiplierInfo {
//String overallScore;



public MultiplierAutoChange(String aMessage,
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

	
	public static MultiplierAutoChange newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			double aScore,
			Object aFinder) {
		String aMessage = "Multiplier Auto Changed to:" + aScore;
		MultiplierAutoChange retVal = new MultiplierAutoChange(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
