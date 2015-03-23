package grader.trace.feature;

import java.awt.Color;

import framework.grading.testing.Checkable;
import grader.assignment.GradingFeature;
import grader.project.graded.OverviewProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.trace.stepper.SerializableStepperInfo;
import grader.trace.stepper.StepperInfo;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class FeatureChecked extends CheckableFeatureInfo {



public FeatureChecked(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, Checkable aFeature,			
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature, aFinder);
		// TODO Auto-generated constructor stub
	}



	
	public static FeatureChecked newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, Checkable aFeature,
			Object aFinder) {
		String aMessage = "Feature: "  + aFeature.getName() + "Checked.";
		FeatureChecked retVal = new FeatureChecked(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFeature,  aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
