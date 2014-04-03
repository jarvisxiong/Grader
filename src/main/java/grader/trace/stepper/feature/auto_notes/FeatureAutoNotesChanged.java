package grader.trace.stepper.feature.auto_notes;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureAutoNotesChanged extends SerializableFeatureAutoScoreInfo {
//String overallNotes;



public FeatureAutoNotesChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aNotes, aFinder);
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

	
	public static FeatureAutoNotesChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getFeatureName() + "  Auto Notes Changed to:" + aNotes;
		FeatureAutoNotesChanged retVal = new FeatureAutoNotesChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature,aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
