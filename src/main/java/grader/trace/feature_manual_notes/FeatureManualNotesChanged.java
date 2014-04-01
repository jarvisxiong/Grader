package grader.trace.feature_manual_notes;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureManualNotesChanged extends SerializableFeatureManualNotesInfo {
//String overallNotes;



public FeatureManualNotesChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
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

	
	public static FeatureManualNotesChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature Manual Notes Manually Changed to:" + aNotes;
		FeatureManualNotesChanged retVal = new FeatureManualNotesChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
