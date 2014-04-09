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
double featureAutoScore;



public OverallScoreInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		featureAutoScore = aScore;
		// TODO Auto-generated constructor stub
	}

public double getFeatureAutoScore() {
	return featureAutoScore;
}



public void setFeatureAutoScore(double featureAutoScore) {
	this.featureAutoScore = featureAutoScore;
}


	

}
