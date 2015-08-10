package grader.trace.multiplier;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class MultiplierUserChange extends SerializableMultiplierInfo {
//String overallScore;



public MultiplierUserChange(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static MultiplierUserChange newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			double aScore,
			Object aFinder) {
		String aMessage = "Multiplier Manually Changed to:" + aScore;
		MultiplierUserChange retVal = new MultiplierUserChange(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
