package grader.trace.multiplier;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;

import java.awt.Color;

public class MultiplierColored extends MultiplierInfo {
	Color featureAutoScoreColor;



public MultiplierColored(String aMessage,
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

	
	public static MultiplierColored newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject,
			Color aColor,
			double aScore,
			Object aFinder) {
		String aMessage = "Multiplier Colored:" + aColor;
		MultiplierColored retVal = new MultiplierColored(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aColor, aScore, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
