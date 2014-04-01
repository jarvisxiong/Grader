package grader.trace.overallNotes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesIncludedInFeedback extends StepperInfo {



public OverallNotesIncludedInFeedback(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static OverallNotesIncludedInFeedback newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Object aFinder) {
		String aMessage = "Overview Notes Recorded for Inclusion in Feedback File";
		OverallNotesIncludedInFeedback retVal = new OverallNotesIncludedInFeedback(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject,  aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
