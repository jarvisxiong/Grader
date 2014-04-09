package grader.trace.overall_score;

import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class OverallScoreInfo extends StepperInfo {
double featureScore;



public OverallScoreInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		featureScore = aScore;
		// TODO -generated constructor stub
	}

public double getFeatureScore() {
	return featureScore;
}



public void setFeatureScore(double featureScore) {
	this.featureScore = featureScore;
}


	

}
