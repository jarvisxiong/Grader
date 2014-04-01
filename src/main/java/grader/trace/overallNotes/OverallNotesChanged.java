package grader.trace.overallNotes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesChanged extends SerializableStepperInfo {
String manualNotes;



public OverallNotesChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		manualNotes = aNotes;
		// TODO Auto-generated constructor stub
	}

public String getManualNotes() {
	return manualNotes;
}



public void setManualNotes(String manualNotes) {
	this.manualNotes = manualNotes;
}

	
	public static OverallNotesChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		String aMessage = "Overview Notes Manually Changed";
		OverallNotesChanged retVal = new OverallNotesChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
