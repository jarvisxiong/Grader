package grader.trace.steppers;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeaturesAutoGraded extends StepperInfo {



public FeaturesAutoGraded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		// TODO Auto-generated constructor stub
	}


	
	public static FeaturesAutoGraded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, 
			Object aFinder) {
		String aMessage = "Features autograded." ;
		FeaturesAutoGraded retVal = new FeaturesAutoGraded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
