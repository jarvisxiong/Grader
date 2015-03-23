package grader.trace.overall_notes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesSaved extends OverallNotesInfo {
	String overviewNotesFileName;



public OverallNotesSaved(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		overviewNotesFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getOverviewNotesFileName() {
	return overviewNotesFileName;
}



public void setOverviewNotesFileName(String overviewNotesFileName) {
	this.overviewNotesFileName = overviewNotesFileName;
}

	
	public static OverallNotesSaved newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Overview Notes Saved to File:" + anOverviewFileName + ". Notes:" + aNotes;
		OverallNotesSaved retVal = new OverallNotesSaved(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
