package grader.trace.multiplier;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.SerializableStepperInfo;
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
