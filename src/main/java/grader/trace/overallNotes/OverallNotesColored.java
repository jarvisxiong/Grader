package grader.trace.overallNotes;

import java.awt.Color;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesColored extends StepperInfo {
	Color overviewNotesColor;



public OverallNotesColored(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Color anOvervewFileName,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		overviewNotesColor = anOvervewFileName;
		// TODO Auto-generated constructor stub
	}

public Color getOverviewNotesColor() {
	return overviewNotesColor;
}



public void setOverviewNotesColor(Color overviewNotesColor) {
	this.overviewNotesColor = overviewNotesColor;
}

	
	public static OverallNotesColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Color anOverviewFileColor,
			Object aFinder) {
		String aMessage = "Overview Notes Colored:" + anOverviewFileColor;
		OverallNotesColored retVal = new OverallNotesColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, anOverviewFileColor, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
