package grader.trace.overall_notes;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.SerializableStepperInfo;
import grader.trace.steppers.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallNotesInfo extends StepperInfo {
String overallNotes;



public OverallNotesInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		overallNotes = aNotes;
		// TODO Auto-generated constructor stub
	}

public String getOverallNotes() {
	return overallNotes;
}



public void setOverallNotes(String overallNotes) {
	this.overallNotes = overallNotes;
}


	

}
