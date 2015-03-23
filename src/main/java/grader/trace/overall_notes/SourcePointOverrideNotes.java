package grader.trace.overall_notes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class SourcePointOverrideNotes extends SerializableOverallNotesInfo {
//String overallNotes;



public SourcePointOverrideNotes(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		overallNotes = aNotes;
		// TODO Auto-generated constructor stub
	}



	
	public static SourcePointOverrideNotes newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		String aMessage = "Overview Notes after Source Points Override: " + aNotes;
		SourcePointOverrideNotes retVal = new SourcePointOverrideNotes(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
