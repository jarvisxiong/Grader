package grader.trace.overall_notes;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

import java.awt.Color;

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
