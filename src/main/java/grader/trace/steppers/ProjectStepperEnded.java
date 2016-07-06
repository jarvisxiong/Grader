package grader.trace.steppers;

import grader.basics.trace.SerializableGraderInfo;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

public class ProjectStepperEnded extends SerializableGraderInfo {
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
