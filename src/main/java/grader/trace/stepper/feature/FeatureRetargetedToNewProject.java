package grader.trace.stepper.feature;

import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureRetargetedToNewProject extends GradingFeatureInfo {
//String overallNotes;


public FeatureRetargetedToNewProject(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, GradingFeature aFeature,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static FeatureRetargetedToNewProject newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, GradingFeature aFeature,
			Object aFinder) {
		String aMessage = "Feature: " + aFeature.getFeatureName() + " retargeted to project of student:" + aProjectStepper.getOnyen();
		FeatureRetargetedToNewProject retVal = new FeatureRetargetedToNewProject(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature,aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
