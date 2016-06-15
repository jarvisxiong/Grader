package grader.trace.overall_score;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.StepperInfo;

public class OverallScoreInfo extends StepperInfo {
double score;



public OverallScoreInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			double aScore,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		score = aScore;
		// TODO -generated constructor stub
	}

public double getScore() {
	return score;
}



public void setScore(double score) {
	this.score = score;
}


	

}
