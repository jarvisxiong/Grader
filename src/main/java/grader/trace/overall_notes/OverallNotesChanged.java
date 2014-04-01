package grader.trace.overall_notes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesChanged extends SerializableOverallNotesInfo {
//String overallNotes;



public OverallNotesChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		overallNotes = aNotes;
		// TODO Auto-generated constructor stub
	}

//public String getOverallNotes() {
//	return overallNotes;
//}
//
//
//
//public void setOverallNotes(String overallNotes) {
//	this.overallNotes = overallNotes;
//}

//@Override
//public String toCSVRow() {
//	return super.toCSVRow() 
//			+ "," + overallNotes;
//}

	
	public static OverallNotesChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		String aMessage = "Overview Notes Manually Changed to:" + aNotes;
		OverallNotesChanged retVal = new OverallNotesChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
