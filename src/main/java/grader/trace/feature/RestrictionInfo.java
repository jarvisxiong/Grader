package grader.trace.feature;

import framework.grading.testing.Feature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class RestrictionInfo extends StepperInfo {
Feature feature;



public RestrictionInfo(String aMessage,
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
