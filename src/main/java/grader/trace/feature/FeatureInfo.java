package grader.trace.feature;

import framework.grading.testing.Feature;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.StepperInfo;

public class FeatureInfo extends StepperInfo {
Feature feature;



public FeatureInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Feature aFeature,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		feature = aFeature;
		// TODO Auto-generated constructor stub
	}

public Feature getFeature() {
	return feature;
}



public void setFeature(Feature aFeature) {
	this.feature = aFeature;
}


	

}
