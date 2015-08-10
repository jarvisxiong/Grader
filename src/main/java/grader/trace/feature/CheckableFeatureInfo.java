package grader.trace.feature;

import framework.grading.testing.Checkable;
import framework.grading.testing.Checkable;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import grader.trace.steppers.SerializableStepperInfo;
import grader.trace.steppers.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class CheckableFeatureInfo extends StepperInfo {
Checkable checkable;



public CheckableFeatureInfo(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject,
			Checkable aCheckable,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		checkable = aCheckable;
		// TODO Auto-generated constructor stub
	}

public Checkable getCheckable() {
	return checkable;
}



public void setCheckable(Checkable aCheckable) {
	this.checkable = aCheckable;
}


	

}
