package grader.trace;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class StepperInfo extends GraderInfo {
	SakaiProjectDatabase sakaiProjectDatabase; 	
	OverviewProjectStepper overviewProjectStepper;
	SakaiProject sakaiProject;
	public StepperInfo(String aMessage, 
			SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper,
			SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aFinder);
		sakaiProjectDatabase = aSakaiProjectDatabase;
		overviewProjectStepper = aProjectStepper;
		sakaiProject = aProject;
	}
	
	

	public SakaiProject getSakaiProject() {
		return sakaiProject;
	}



	public SakaiProjectDatabase getSakaiProjectDatabase() {
		return sakaiProjectDatabase;
	}

	public OverviewProjectStepper getOverviewProjectStepper() {
		return overviewProjectStepper;
	}
	
//	public static StepperInfo newCaseObject(SakaiProjectDatabase aSakaiProjectDatabase, 
//			OverviewProjectStepper aProjectStepper, 
//			SakaiProject aProject,
//			Object aFinder) {
//		String aMessage = "Navigation Initiated";
//		StepperInfo retVal = new StepperInfo(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
//		retVal.announce();		
//		return retVal;
//	}
	

}
