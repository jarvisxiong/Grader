package grader.trace.overall_notes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesIncludedInFeedback extends OverallNotesInfo {



public OverallNotesIncludedInFeedback(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		// TODO Auto-generated constructor stub
	}


	
	public static OverallNotesIncludedInFeedback newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		String aMessage = "Overall Notes Recorded for Inclusion in Feedback File:" + aNotes;
		OverallNotesIncludedInFeedback retVal = new OverallNotesIncludedInFeedback(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes,  aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
