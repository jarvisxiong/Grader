package grader.trace.steppers;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class ProjectIORedirected extends StepperInfo {
public ProjectIORedirected(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		// TODO Auto-generated constructor stub
	}


	
	public static ProjectIORedirected newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Object aFinder) {
		String aMessage = "Windows Cleared";
		ProjectIORedirected retVal = new ProjectIORedirected(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
