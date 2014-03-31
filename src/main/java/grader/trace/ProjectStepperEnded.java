package grader.trace;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProjectStepperEnded extends TraceableInfo {
	SakaiProjectDatabase sakaiProjectDatabase; 	
	OverviewProjectStepper overviewProjectStepper;
	public ProjectStepperEnded(String aMessage, SakaiProjectDatabase aSakaiProjectDatabase, OverviewProjectStepper aProjectStepper, Object aFinder) {
		super(aMessage, aFinder);
		sakaiProjectDatabase = aSakaiProjectDatabase;
		overviewProjectStepper = aProjectStepper;
	}
	
	

	public SakaiProjectDatabase getSakaiProjectDatabase() {
		return sakaiProjectDatabase;
	}

	public OverviewProjectStepper getOverviewProjectStepper() {
		return overviewProjectStepper;
	}
	
	public static ProjectStepperEnded newCase(SakaiProjectDatabase aSakaiProjectDatabase, OverviewProjectStepper aProjectStepper, Object aFinder) {
		String aMessage = "Navigation Terminated";
		ProjectStepperEnded retVal = new ProjectStepperEnded(aMessage, aSakaiProjectDatabase, aProjectStepper, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
