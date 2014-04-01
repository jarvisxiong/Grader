package grader.trace.feature_manual_notes;

import java.awt.Color;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureManualNotesColored extends FeatureManualNotesInfo {
	Color featureManualNotesColor;



public FeatureManualNotesColored(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Color aColor,
			String aNotes,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aNotes, aFinder);
		featureManualNotesColor = aColor;
		// TODO Auto-generated constructor stub
	}

public Color getFeatureManualNotesColor() {
	return featureManualNotesColor;
}



public void setFeatureManualNotesColor(Color featureManualNotesColor) {
	this.featureManualNotesColor = featureManualNotesColor;
}

	
	public static FeatureManualNotesColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Color aColor,
			String aNotes,
			Object aFinder) {
		String aMessage = "Feature Manual Notes Colored:" + aColor;
		FeatureManualNotesColored retVal = new FeatureManualNotesColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aColor, aNotes, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
