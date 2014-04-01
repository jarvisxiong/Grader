package grader.trace.stepper;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProjectStepperStarted extends TraceableInfo {
	SakaiProjectDatabase sakaiProjectDatabase; 	
	OverviewProjectStepper overviewProjectStepper;
	public ProjectStepperStarted(String aMessage, SakaiProjectDatabase aSakaiProjectDatabase, OverviewProjectStepper aProjectStepper, Object aFinder) {
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
	
	public static ProjectStepperStarted newCase(SakaiProjectDatabase aSakaiProjectDatabase, OverviewProjectStepper aProjectStepper, Object aFinder) {
		String aMessage = "Navigation Initiated";
		ProjectStepperStarted retVal = new ProjectStepperStarted(aMessage, aSakaiProjectDatabase, aProjectStepper, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
