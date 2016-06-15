package grader.trace.overall_score;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

import java.awt.Color;

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
		String aMessage = "Overall Score Colored:" + aColor;
		OverallScoreColored retVal = new OverallScoreColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aColor, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
