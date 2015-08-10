package grader.trace.steppers;

import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.steppers.OverviewProjectStepper;
import bus.uigen.trace.ConstantsMenuAdditionEnded;
import util.trace.TraceableInfo;

public class HasMoreStepsChanged extends StepperInfo {
	boolean hasMoreSteps;
public boolean isHasMoreSteps() {
		return hasMoreSteps;
	}



	public void setHasMoreSteps(boolean hasMoreSteps) {
		this.hasMoreSteps = hasMoreSteps;
	}



public HasMoreStepsChanged(String aMessage,
			SakaiProjectDatabase aSakaiProjectDatabase,
			OverviewProjectStepper aProjectStepper, SakaiProject aProject, boolean newVal,
			Object aFinder) {
		super(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, aFinder);
		hasMoreSteps = newVal;
		// TODO Auto-generated constructor stub
	}


	
	public static HasMoreStepsChanged newCase(SakaiProjectDatabase aSakaiProjectDatabase, 
			OverviewProjectStepper aProjectStepper, 
			SakaiProject aProject, boolean newVal,
			Object aFinder) {
		String aMessage = "Proceed when done changed to:" + newVal;
		HasMoreStepsChanged retVal = new HasMoreStepsChanged(aMessage, aSakaiProjectDatabase, aProjectStepper, aProject, newVal, aFinder);
		retVal.announce();		
		return retVal;
	}
	

}
