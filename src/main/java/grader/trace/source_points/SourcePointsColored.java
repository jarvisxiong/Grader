package grader.trace.source_points;

import java.awt.Color;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.SerializableStepperInfo;
import grader.trace.steppers.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class SourcePointsColored extends SourcePointsInfo {
	Color featureAutoScoreColor;



public SourcePointsColored(String aMessage,
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

	
	public static SourcePointsColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Color aColor,
			double aScore,
			Object aFinder) {
		String aMessage = "Source Points Colored:" + aColor;
		SourcePointsColored retVal = new SourcePointsColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aColor, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
