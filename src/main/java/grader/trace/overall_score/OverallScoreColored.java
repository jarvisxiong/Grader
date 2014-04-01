package grader.trace.overall_score;

import java.awt.Color;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallScoreColored extends OverallScoreInfo {
	Color featureAutoScoreColor;



public OverallScoreColored(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Color aColor,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aScore, aFinder);
		featureAutoScoreColor = aColor;
		// TODO Auto-generated constructor stub
	}

public Color getFeatureAutoScoreColor() {
	return featureAutoScoreColor;
}



public void setFeatureAutoScoreColor(Color featureAutoScoreColor) {
	this.featureAutoScoreColor = featureAutoScoreColor;
}

	
	public static OverallScoreColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Color aColor,
			double aScore,
			Object aFinder) {
		String aMessage = "Feature Auto Score Colored:" + aColor;
		OverallScoreColored retVal = new OverallScoreColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aColor, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
