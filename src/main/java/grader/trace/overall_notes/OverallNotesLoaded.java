package grader.trace.overall_notes;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesLoaded extends StepperInfo {
	String overviewNotesFileName;



public OverallNotesLoaded(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String anOvervewFileName,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		overviewNotesFileName = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public String getOverviewNotesFileName() {
	return overviewNotesFileName;
}



public void setOverviewNotesFileName(String overviewNotesFileName) {
	this.overviewNotesFileName = overviewNotesFileName;
}

	
	public static OverallNotesLoaded newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String anOverviewFileName,
			String aNotes,
			Object aFinder) {
		String aMessage = "Overview Notes Loaded from File:" + anOverviewFileName + ". Notes:" + aNotes;
		OverallNotesLoaded retVal = new OverallNotesLoaded(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileName, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
