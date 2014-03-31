package grader.trace;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProjectStepStarted extends StepperInfo {
public ProjectStepStarted(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		// TODO Auto-generated constructor stub
	}


	
	public static ProjectStepStarted newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Object aFinder) {
		String aMessage = "Navigation Initiated";
		ProjectStepStarted retVal = new ProjectStepStarted(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
