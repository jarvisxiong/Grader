package grader.trace;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProjectStepEnded extends StepperInfo {
	public ProjectStepEnded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
	}




	SakaiProjectDatabase sakaiProjectDatabase; 	
	OverviewProjectStepper overviewProjectStepper;
	SakaiProject sakaiProject;
	
	
	

//	public SakaiProject getSakaiProject() {
//		return sakaiProject;
//	}
//
//
//
//	public SakaiProjectDatabase getSakaiProjectDatabase() {
//		return sakaiProjectDatabase;
//	}
//
//	public OverviewProjectStepper getOverviewProjectStepper() {
//		return overviewProjectStepper;
//	}
	
	public static ProjectStepEnded newCaseObject(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Object aFinder) {
		String aMessage = "Navigation Initiated";
		ProjectStepEnded retVal = new ProjectStepEnded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
