package grader.trace.overall_notes;

import java.awt.Color;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesColored extends OverallNotesInfo {
	Color overviewNotesColor;



public OverallNotesColored(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Color aColor,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		overviewNotesColor = aColor;
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
			Color aColor,
			String aNotes,
			Object aFinder) {
		String aMessage = "Overview Notes Colored:" + aColor;
		OverallNotesColored retVal = new OverallNotesColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aColor, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
