package grader.trace.overall_notes;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

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
